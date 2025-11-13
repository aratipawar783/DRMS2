package com.example.drms2.admin.adminCM.adminFYCM;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CropViewFYCM extends View {

    private Paint paint;
    private Rect cropRect;
    private Bitmap bitmap;
    private float startX, startY;
    private boolean isSelecting = false;

    public CropViewFYCM(Context context) {
        super(context);
        init();
    }

    public CropViewFYCM(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        cropRect = new Rect();
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        invalidate(); // Force a re-draw to update the bitmap
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (bitmap != null) {
            // Draw the bitmap onto the canvas
            canvas.drawBitmap(bitmap, 0, 0, null);
        }

        if (isSelecting) {
            // Draw the crop rectangle when selecting
            canvas.drawRect(cropRect, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // Start selection
                startX = x;
                startY = y;
                isSelecting = true;
                break;
            case MotionEvent.ACTION_MOVE:
                // Update the crop rectangle as the user moves their finger
                cropRect.set(
                        (int) Math.min(startX, x),
                        (int) Math.min(startY, y),
                        (int) Math.max(startX, x),
                        (int) Math.max(startY, y)
                );
                invalidate(); // Redraw to update the crop area
                break;
            case MotionEvent.ACTION_UP:
                // Finish selection
                isSelecting = false;
                cropImage(); // Call the crop method when the user releases the touch
                break;
        }

        return true;
    }

    // Crop the selected area
    public void cropImage() {
        if (bitmap != null && cropRect.width() > 0 && cropRect.height() > 0) {
            Bitmap croppedBitmap = Bitmap.createBitmap(
                    bitmap,
                    cropRect.left,
                    cropRect.top,
                    cropRect.width(),
                    cropRect.height()
            );
            if (getContext() instanceof MiddleActivityFYCM) {
                ((MiddleActivityFYCM) getContext()).setCroppedImage(croppedBitmap); // Pass cropped bitmap to MainActivity
            }
        }
    }
}
