package com.example.smartcropapp.soil;

import android.content.Context;
import android.graphics.Bitmap;



import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.List;

public class SoilClassifier {
    private Interpreter interpreter;
    private List<String> labels;

    public SoilClassifier(Context context) throws IOException {
        ByteBuffer model = loadModelFile(context, "soil_classifier.tflite");
        interpreter = new Interpreter(model);
        labels = Utils.loadLabels(context, "labels.txt");
    }

    private ByteBuffer loadModelFile(Context context, String modelName) throws IOException {
        FileInputStream input = new FileInputStream(context.getAssets().openFd(modelName).getFileDescriptor());
        FileChannel fileChannel = input.getChannel();
        long startOffset = context.getAssets().openFd(modelName).getStartOffset();
        long declaredLength = context.getAssets().openFd(modelName).getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    public Prediction classify(Bitmap bitmap) {
        Bitmap resized = Bitmap.createScaledBitmap(bitmap, 224, 224, true);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * 224 * 224 * 3);
        byteBuffer.order(ByteOrder.nativeOrder());

        int[] pixels = new int[224 * 224];
        resized.getPixels(pixels, 0, 224, 0, 0, 224, 224);
        for (int pixel : pixels) {
            float r = ((pixel >> 16) & 0xFF) / 255.0f;
            float g = ((pixel >> 8) & 0xFF) / 255.0f;
            float b = (pixel & 0xFF) / 255.0f;
            byteBuffer.putFloat(r);
            byteBuffer.putFloat(g);
            byteBuffer.putFloat(b);
        }

        TensorBuffer output = TensorBuffer.createFixedSize(new int[]{1, labels.size()}, org.tensorflow.lite.DataType.FLOAT32);
        interpreter.run(byteBuffer, output.getBuffer().rewind());

        float[] scores = output.getFloatArray();
        int maxIdx = 0;
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > scores[maxIdx]) maxIdx = i;
        }

        return new Prediction(labels.get(maxIdx), scores[maxIdx]);
    }

    public static class Prediction {
        public String soilType;
        public float confidence;

        public Prediction(String soilType, float confidence) {
            this.soilType = soilType;
            this.confidence = confidence;
        }
    }
}
