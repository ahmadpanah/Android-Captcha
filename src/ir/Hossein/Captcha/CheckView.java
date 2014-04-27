package ir.Hossein.Captcha;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class CheckView extends View implements CheckAction
{

	Context mContext;
	int [] CheckNum = {0,0,0,0};
	Paint mTempPaint = new Paint();
	
	public CheckView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		
		mContext = context;
		mTempPaint.setAntiAlias(true);
		mTempPaint.setTextSize(15);
		mTempPaint.setStrokeWidth(3);
	}
	
	public void onDraw(Canvas canvas)
	{
		canvas.drawColor(Color.WHITE);
		
		final int height = getHeight();
		final int width = getWidth();
		int dx = 40;
		for(int i = 0; i < 4; i ++)
			{
			canvas.drawText("" + CheckNum[i],  dx, CheckGetUtil.getPositon(height), mTempPaint);
			dx += width/ 5;
			}
		int [] line;
		for(int i = 0; i < ConmentConfig.LINE_NUM; i ++)
			{
			line = CheckGetUtil.getLine(height, width);
			canvas.drawLine(line[0], line[1], line[2], line[3], mTempPaint);
			}
		int [] point;
		for(int i = 0; i < ConmentConfig.POINT_NUM; i ++)	
			{
			point=CheckGetUtil.getPoint(height, width);
			canvas.drawCircle(point[0], point[1], 1, mTempPaint);
			}
	}
	
	public void setCheckNum(int [] chenckNum) 
	{
		CheckNum = chenckNum;
	}
	
	public int[] getCheckNum() 
	{
		return CheckNum;
	}
	
	public void invaliChenkNum() 
	{
		invalidate();
	}
	
}
