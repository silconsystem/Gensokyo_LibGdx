package com.silconsystem.gensokyo.utils;

// import my packs
import com.silconsystem.gensokyo.R;

// import android packs
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class ExitDialog {
	
	protected final View anchor;
	private final PopupWindow window;
	private View root;
	private final WindowManager windowManager;
	
	/***
	 * 
	 * 		Create a PopupWindow with customized settings
	 * 
	 * @param anchor
	 */
	public ExitDialog(View anchor)
	{
		this.anchor = anchor;
		this.window = new PopupWindow(anchor.getContext());
		
		// touch registerd outside window area 
		// makes window lose focus and is dismissed
		this.window.setTouchInterceptor(new OnTouchListener(){
			
			@Override
			public boolean onTouch(View view, MotionEvent event)
			{
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE)
				{
					ExitDialog.this.window.dismiss();
					return true;
				}
				return false;
			}
		});
		
		// initialize the windowmanager
		this.windowManager = (WindowManager)this.anchor.getContext().getSystemService(Context.WINDOW_SERVICE);
		onCreate();
	
	}
	
	/***
	 * 		Window Parameters
	 ***/
	protected void onCreate()
	{
		/* TODO: add behaviours on creation */
	}
	
	protected void onShow()
	{
		/* TODO: add behaviours before show */
	}
	
	private void preShow()
	{
		if (this.root == null)
		{
			throw new IllegalStateException("setContentView was not called with a view to display");
		}
		onShow();
		
		this.window.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
		this.window.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
		this.window.setTouchable(true);
		this.window.setFocusable(true);
		this.window.setOutsideTouchable(true);
		this.window.setContentView(this.root);		
	}
	
	public void setBackgroundDrawable(Drawable background)
	{
	}
	
	/***
	 * 		Set Content View
	 * @param root
	 * sets the view the popup will display
	 * 
	 ***/	
	public void setContentView(View root)
	{
		this.root = root;
		this.window.setContentView(root);
	}
	
	/***
	 * 		Inflate and set the View from a resource ID
	 * 
	 * @param layoutResID
	 ***/
	public void setContentView(int layoutResID)
	{
		LayoutInflater inflator = (LayoutInflater) this.anchor.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                this.setContentView(inflator.inflate(layoutResID, null));
	}
	
	/***
         * If you want to do anything when {@link dismiss} is called
         * 
         * @param listener
         * 
         ***/
	public void setOnDismissListener(PopupWindow.OnDismissListener listener)
	{
                this.window.setOnDismissListener(listener);
        }
	
	/***
	 * 		Display like a popdown menu from anchor View
	 * 
	 * @param xOffset (offset in X direction)
	 * @param yOffset (offset in Y direction)
	 */
	public void showLikePopDownMenu(int xOffset, int yOffset)
	{
		this.preShow();
		this.window.setAnimationStyle(R.style.AppBaseTheme);
		this.window.showAsDropDown(this.anchor, xOffset, yOffset);
	}	
	
	/***
	 * 		Display like a QuickAction from the anchor View
	 * 
	 * @param xOffset (offset in X direction)
	 * @param yOffset (offset in Y direction)
	 ***/
	public void showLikeQuickAction(int xOffset, int yOffset)
	{
		this.preShow();
		this.window.setAnimationStyle(R.style.AppBaseTheme);
		
		// get anchor location
		int[] location = new int[2];
		this.anchor.getLocationOnScreen(location);
		
		// create a rectangle
		Rect anchorRect = new Rect(location[0], location[1],
				location[0] + this.anchor.getWidth(),
				location[1] + this.anchor.getHeight()
				);
		
		// wrap the content
		this.root.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		// get root width and height
		int rootWidth = this.root.getMeasuredWidth();
		int rootHeight = this.root.getMeasuredHeight();
		
		// get display width and height, initialize displaymetrics first
		// to workaround deprecated methods
		DisplayMetrics metrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(metrics);
		
		int screenWidth = metrics.widthPixels;
		/*int screenHeight = metrics.heightPixels; NOT USED YET*/
				
		// calculate  X & Y position
		int xPos = ((screenWidth - rootWidth) /2 ) + xOffset;
		int yPos = anchorRect.top - rootHeight + yOffset;
		
		// display window at the bottom af the screen
		if (rootHeight > anchorRect.top)
		{
			yPos = anchorRect.bottom + yOffset;
			this.window.setAnimationStyle(R.style.AppBaseTheme);
		}
		
		this.window.showAtLocation(this.anchor, Gravity.NO_GRAVITY, xPos, yPos);
	}
	
	/***
	 * 			Dismiss the Window
	 ***/
	public void dismiss()
	{
		this.window.dismiss();
	}
	
}
