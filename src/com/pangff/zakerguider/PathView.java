package com.pangff.zakerguider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.util.AttributeSet;
import android.view.View;

public class PathView  extends View{  
    private Path mPath = new Path();  
    private Matrix matrix = new Matrix();  
    private Bitmap bitmap;  
    private Bitmap bitmap2;  
    //放大镜的半径  
    public static int RADIUS = 80;  
    //放大倍数  
    private static final int FACTOR = 2;  
    private static final int STOCK = 15;  
  
    public PathView(Context context) {  
        super(context);  
        matrix.setScale(FACTOR, FACTOR);  
    }     
    
    
    
    public PathView(Context context, AttributeSet attrs) {
		super(context, attrs);
		matrix.setScale(FACTOR, FACTOR);  
	}


    /**
     * 按正方形裁切图片
     */
    public static Bitmap ImageCrop(Bitmap bitmap) {
    		if(bitmap==null){
    			return null;
    		}
        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();

        int wh = w;// 裁切后所取的正方形区域边长

        int retX =  0;//基于原图，取正方形左上角x坐标
        int retY = 0;

        //下面这句是关键
        return Bitmap.createBitmap(bitmap, retX, retY, wh, wh, null, false);
    }

	public void setBitMap(Bitmap bitmap){
    	   this.bitmap = bitmap;
    	   bitmap2 =  ImageCrop(bitmap);
    	   invalidate();
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    		RADIUS = this.getMeasuredWidth()/2-STOCK;
    	    mPath.addCircle(RADIUS, RADIUS, RADIUS, Direction.CW);  
    	    bitmap2 =  ImageCrop(bitmap);;
    }
      
    @Override  
    public void onDraw(Canvas canvas) {  
        super.onDraw(canvas); 
        if(bitmap2!=null){
          	//canvas.drawBitmap(bitmap, matrix, null);   
            //剪切  
        		canvas.translate(STOCK,STOCK); 
            canvas.clipPath(mPath);   
            //画放大后的图  
            canvas.drawBitmap(bitmap2, matrix, null);   
        }
    }  
}
