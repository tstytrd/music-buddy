package edu.luc.mb;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;

public class NoteSynthesizer {

    private static final int SAMPLE_RATE = 44100;

    /**
     * Synthesizes and plays a note of given frequency with a piano-like harmonic decay.
     * Runs asynchronously on a background thread so UI is never blocked.
     *
     * @param frequency Frequency in Hz (e.g. 261.63 for C4, 440.0 for A4)
     * @param durationMs Duration of the note in milliseconds
     */
    public static void playFrequency(final double frequency, final int durationMs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int numSamples = (int) (SAMPLE_RATE * (durationMs / 1000.0));
                    short[] sample = new short[numSamples];

                    // Generate tone with fundamental + harmonics with exponential decay
                    for (int i = 0; i < numSamples; ++i) {
                        double time = (double) i / SAMPLE_RATE;
                        // Exponential envelope (pluck/piano decay)
                        double envelope = Math.exp(-3.5 * time / (durationMs / 1000.0));

                        // Fundamental + 2nd harmonic (warmth) + 3rd harmonic
                        double wave = 0.65 * Math.sin(2.0 * Math.PI * frequency * time)
                                    + 0.25 * Math.sin(2.0 * Math.PI * (frequency * 2.0) * time)
                                    + 0.10 * Math.sin(2.0 * Math.PI * (frequency * 3.0) * time);

                        sample[i] = (short) (wave * envelope * Short.MAX_VALUE);
                    }

                    int bufferSize = AudioTrack.getMinBufferSize(
                            SAMPLE_RATE,
                            AudioFormat.CHANNEL_OUT_MONO,
                            AudioFormat.ENCODING_PCM_16BIT
                    );

                    AudioTrack audioTrack = new AudioTrack.Builder()
                            .setAudioAttributes(new AudioAttributes.Builder()
                                    .setUsage(AudioAttributes.USAGE_MEDIA)
                                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                    .build())
                            .setAudioFormat(new AudioFormat.Builder()
                                    .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                                    .setSampleRate(SAMPLE_RATE)
                                    .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                                    .build())
                            .setBufferSizeInBytes(Math.max(bufferSize, sample.length * 2))
                            .setTransferMode(AudioTrack.MODE_STATIC)
                            .build();

                    audioTrack.write(sample, 0, sample.length);
                    audioTrack.play();

                    // Wait until playback finishes, then release
                    Thread.sleep(durationMs + 100);
                    audioTrack.stop();
                    audioTrack.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Plays a sequence of notes (melody/tune) with a small gap in between.
     */
    public static void playMelody(final double[] frequencies, final int noteDurationMs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (double freq : frequencies) {
                    playFrequency(freq, noteDurationMs);
                    try {
                        Thread.sleep(noteDurationMs + 50);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }).start();
    }
}