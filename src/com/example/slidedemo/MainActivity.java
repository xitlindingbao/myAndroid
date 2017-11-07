package com.example.slidedemo;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends SlidingFragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		createSlidingMenu();
	}

	private void createSlidingMenu() {
		// 1、初始化SlidingMenu
		SlidingMenu sm = this.getSlidingMenu();
		// 2、设置SlidingMenu阴影
		sm.setShadowWidthRes(R.dimen.shadow_width);
		// 阴影drawable
		sm.setShadowDrawable(R.drawable.shadow);
		// 是否有阴影
		// sm.setShadowDrawable(true);

		// 3、设置SlidingMenu拉开后离边框距离
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);

		// 4、设置渐变:是否有渐变 ,渐变比率
		sm.setFadeEnabled(true);
		sm.setFadeDegree(0.35f);

		// 5、设置SlidingMenu布局
		this.setBehindContentView(R.layout.slide_menu_left_frame);
		this.getSupportFragmentManager().beginTransaction()
				.replace(R.id.slide_left, new LeftMenuFragment()).commit();
		// 6、设置模式
		sm.setMode(SlidingMenu.LEFT);

		// 如果设置左右两边滑动，则需要设置第二个布局
		sm.setMode(SlidingMenu.LEFT_RIGHT);
		// 此时要再次添加布局菜单，上一个为左侧，这个为右侧
		sm.setSecondaryMenu(R.layout.slide_menu_right_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.slide_right, new RightMenuFragment()).commit();
		sm.setSecondaryShadowDrawable(R.drawable.shadow);

		// 7、设置划动模式：全屏
		/*
		 * SlidingMenu.setTouchModeAbove().其中一共包含三中手势模式：
		 * TOUCHMODE_FULLSCREEN：全屏模式，在正文布局中通过手势也可以打开SlidingMenu
		 * TOUCHMODE_MARGIN：边缘模式，在正文布局的边缘处通过手势可以找开SlidingMenu
		 * TOUCHMODE_NONE：自然是不能通过手势打开SlidingMenu了
		 */
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		// 8、设置SldingMenu自动判断当前是打开还是关闭：
		toggle();

		// 9、设置缩放比例
		getSlidingMenu().setBehindScrollScale((float) 0.5);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
