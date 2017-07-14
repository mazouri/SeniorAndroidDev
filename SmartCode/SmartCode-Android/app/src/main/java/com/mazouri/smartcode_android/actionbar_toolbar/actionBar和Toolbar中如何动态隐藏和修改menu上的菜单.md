# actionBar和Toolbar中如何动态隐藏和修改menu上的菜单

[actionBar和Toolbar中如何动态隐藏和修改menu上的菜单](http://blog.csdn.net/chenguang79/article/details/48826199)

主要方法：
- onCreateOptionsMenu
- onOptionsItemSelected
- onPrepareOptionsMenu
- 在切换的地方：actionbar：this.getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
- 在切换的地方：Toolbar：invalidateOptionsMenu();