package com.mazouri.smartcode_android.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.assist.AssistContent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.mazouri.smartcode_android.R;

import java.util.List;

/**
 * Activity中的重要方法
 *
 *
 * 继承关系：
 * {@link AppCompatActivity} -> {@link FragmentActivity}
 * -> {@link android.support.v4.app.BaseFragmentActivityJB} -> {@link android.support.v4.app.BaseFragmentActivityHoneycomb}
 * -> {@link android.support.v4.app.BaseFragmentActivityGingerbread} -> {@link android.support.v4.app.SupportActivity}
 * -> {@link android.app.Activity} -> {@link android.view.ContextThemeWrapper} -> {@link android.content.ContextWrapper}
 * -> {@link android.content.Context} -> Object
 */
public class BasicActivity extends AppCompatActivity {
    private static final String TAG = BasicActivity.class.getSimpleName();

    /**
     * attachBaseContext会在onCreate前调用，在这之后才能用 context
     *
     * 每次重新创建时会调用这个方法，比如旋转屏幕后
     *
     * @param newBase
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);

        Log.d(TAG, "attachBaseContext called");
    }

    /**
     * {@link #onSaveInstanceState(Bundle)}
     * {@link #onRestoreInstanceState(Bundle)}
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");

        setContentView(R.layout.activity_basic);

        //通过handler切换到主线程
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //这个任务将在UI线程中运行
            }
        });
    }

    /**
     * 该方法与manifest中Activity中的android:persistableMode有关系，是Android5.0新增方法，
     *
     * 当设置成persistAcrossReboots，系统关机重启后会回调该方法，进行数据恢复，再由该方法调用{@link #onCreate(Bundle)}方法
     *
     * Bundle 与 PersistableBundle 区别:http://blog.csdn.net/luojiusan520/article/details/51462462
     *
     * [Android实战技巧之二十六：persistableMode与Activity的持久化](http://blog.csdn.net/lincyang/article/details/45287599)
     *
     * {@link #onSaveInstanceState(Bundle, PersistableBundle)}
     * {@link #onRestoreInstanceState(Bundle, PersistableBundle)}
     *
     * @param savedInstanceState
     * @param persistentState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    /**
     *
     * 锁屏之后重新显示，执行流程onRestart --> onStart --> onResume
     *
     * {@link #onStop()} -> {@link #onRestart()} -> {@link #onStart()}
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called");
    }

    /**
     * 在方法的调用时，是不会执行onCreate、onStart方法，而是直接执行：onNewIntent --> onResume，
     *
     * 总之就是复用原来的Activity实例。
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent called");
    }

    /**
     * 在Activity的生命周期中，onCreate()--onStart()--onResume()都不是窗体Visible的时间点，
     *
     * 真正的窗体完成初始化可见获取焦点可交互是在{@link #onWindowFocusChanged(boolean)}方法被执行时，而这之前，对用户的操作需要做一点限制。
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void finishActivity(int requestCode) {
        super.finishActivity(requestCode);
    }

    @Override
    public void finishActivityFromChild(@NonNull Activity child, int requestCode) {
        super.finishActivityFromChild(child, requestCode);
    }

    @Override
    public void finishAffinity() {
        super.finishAffinity();
    }

    @Override
    public void finishAfterTransition() {
        super.finishAfterTransition();
    }

    @Override
    public void finishAndRemoveTask() {
        super.finishAndRemoveTask();
    }

    /**
     * 说明： nonRoot=false→ 仅当activity为task根（即首个activity例如启动activity之类的）时才生效，
     *      nonRoot=true→ 忽略上面的限制这个方法不会改变task中的activity中的顺序，效果基本等同于home键。
     *
     * 作用：比如有些activity诸如引导图之类的，用户在按返回键的时候你并不希望退出（默认就finish了），
     *      而是只希望置后台，就可以调这个方法
     * @param nonRoot
     * @return
     */
    @Override
    public boolean moveTaskToBack(boolean nonRoot) {
        return super.moveTaskToBack(nonRoot);
    }

    /**
     * 如果此Activity是所在Task中的第一个Activity返回true
     * @return
     */
    @Override
    public boolean isTaskRoot() {
        return super.isTaskRoot();
    }

    /**
     * 固定屏幕，使用该方法时用户不能切换其他app。实际上就是锁定当前的task
     *
     * 开了屏幕固定以后，通知栏和状态栏会隐藏，home键和recent键会失效（单独按会失效），然后还不准启动其他activity。
     * 就是说 你只能在这个应用内部干事情。比如你吧手机借给别人的时候就可以用这个功能
     */
    @Override
    public void startLockTask() {
        super.startLockTask();
    }

    /**
     * 关闭固定屏幕
     */
    @Override
    public void stopLockTask() {
        super.stopLockTask();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
    }

    /**
     * 通过重写Activity的onCreatePanelView()方法提供一个完全自定义的OptionMenu视图，即自定义menu
     *
     * @param featureId
     * @param menu
     * @return
     */
    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        return super.onPreparePanel(featureId, view, menu);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return super.onRetainCustomNonConfigurationInstance();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed called");
    }

    /**
     * 向系统报告该app已经完全显示出来了，仅仅是用于帮助测量app的启动时间
     */
    @Override
    public void reportFullyDrawn() {
        super.reportFullyDrawn();
        Log.d(TAG, "reportFullyDrawn called time:"+System.currentTimeMillis());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged called");
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    /**
     * 在系统内存不足，所有后台程序（优先级为background的进程，不是指后台运行的进程）都被杀死时，系统会调用OnLowMemory。
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "onLowMemory called");
    }

    /**
     * 系统会根据不同的内存状态来回调。例如按Home键时就会调用。
     * 作用：释放缓存包括一些文件缓存，图片缓存等 或 一些动态生成动态添加的View，
     *      这些动态生成和添加的View且少数情况下才使用到的View，这时候可以被释放。
     *
     * OnLowMemory和OnTrimMemory的比较:
     *      OnLowMemory被回调时，已经没有后台进程；
     *      而onTrimMemory被回调时，还有后台进程
     *
     * @param level
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(TAG, "onTrimMemory called level:" + level);
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        super.onPanelClosed(featureId, menu);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onSupportActionModeFinished(@NonNull android.support.v7.view.ActionMode mode) {
        super.onSupportActionModeFinished(mode);
    }

    @Override
    public void onSupportActionModeStarted(@NonNull android.support.v7.view.ActionMode mode) {
        super.onSupportActionModeStarted(mode);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        return super.onCreateDialog(id);
    }

    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        return super.onCreateDialog(id, args);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
        super.onPrepareDialog(id, dialog, args);
    }

    @Override
    public void onSupportContentChanged() {
        super.onSupportContentChanged();
    }

    /**
     * Activity为 Dialog样式
     *
     * 当activity设置为对话框样式时，通过该方法可以设置点击对话框外的区别触发是否销毁activity
     *
     * @param finish
     */
    @Override
    public void setFinishOnTouchOutside(boolean finish) {
        super.setFinishOnTouchOutside(finish);
        Log.d(TAG, "setFinishOnTouchOutside called finish：" + finish);
    }

    @Nullable
    @Override
    public ActionBar getActionBar() {
        return super.getActionBar();
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        super.addContentView(view, params);
    }

    /**
     * 设置从一个activity跳转到另外一个activity时的切换动画
     *
     * @param enterAnim
     * @param exitAnim
     */
    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(enterAnim, exitAnim);
    }

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, @StyleRes int resid, boolean first) {
        super.onApplyThemeResource(theme, resid, first);
    }

    @Override
    protected void onChildTitleChanged(Activity childActivity, CharSequence title) {
        super.onChildTitleChanged(childActivity, title);
    }

    /**
     * onRestoreInstanceState是在onStart到onResume期间，onSaveInstanceState方法和onRestoreInstanceState方法“不一定”是成对的被调用
     *
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    /**
     * 当系统“未经你许可”时销毁了你的activity，则onSaveInstanceState会被系统调用
     *
     * 调用顺序：{@link #onPause()} -> {@link #onSaveInstanceState(Bundle, PersistableBundle)} -> {@link #onStop()}
     *
     * @param outState
     * @param outPersistentState
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStateNotSaved() {
        super.onStateNotSaved();
    }

    /**
     * 当按键、触屏或轨迹球事件分发到该activity时就会回调该方法
     *
     * 在activity的{@link #dispatchKeyEvent(KeyEvent)}方法中，首先就是调用该方法
     */
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        Log.d(TAG, "onUserInteraction called");
    }

    /**
     * 当该Activity即将由前台状态变成后台状态时会调用该方法，比如按下Home按键或是启动一个新的Activity。
     *
     * 该方法的调用时间是在onPause之前的，此时还是前台状态，当由电话打断当前Activity时，并不会触发该方法。
     *
     * 使用场景：Android主页键和最近应用键的处理onUserLeaveHint。
     */
    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Log.d(TAG, "onUserLeaveHint called");
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {
        super.onActionModeStarted(mode);
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
    }

    /**
     * onResume -> onAttachedToWindow -> onWindowFocusChanged
     */
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow called");
    }

    /**
     * onPause -> onWindowFocusChanged -> onStop -> onDestroy -> onDetachedFromWindow
     */
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow called");
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return super.onWindowStartingActionMode(callback);
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        return super.onWindowStartingActionMode(callback, type);
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        super.onWindowAttributesChanged(params);
    }

    /**
     *
     * 一个Activity一加载完毕会调用
     *
     * 当窗口的焦点状态改变时就会回调该方法
     *
     * onResume--> onPostResume --> onWindowFocusChanged
     *
     * 添加窗体在视图初始化完成（也就是在这个方法之后）过后，否则会报bug :unable to add window -- token null is not
     *
     * 在Activity的生命周期中，onCreate()--onStart()--onResume()都不是窗体Visible的时间点，
     *
     * 真正的窗体完成初始化可见获取焦点可交互是在onWindowFocusChanged()方法被执行时，而这之前，对用户的操作需要做一点限制。
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "onWindowFocusChanged called hasFocus:" + hasFocus);
    }

    /**
     * 获取activity主窗口的焦点状态，该状态与窗口中的View的状态是不一样的
     *
     * @return
     */
    @Override
    public boolean hasWindowFocus() {
        return super.hasWindowFocus();
    }

    @Nullable
    @Override
    public android.support.v7.view.ActionMode onWindowStartingSupportActionMode(@NonNull android.support.v7.view.ActionMode.Callback callback) {
        return super.onWindowStartingSupportActionMode(callback);
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public void onContextMenuClosed(Menu menu) {
        super.onContextMenuClosed(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {
        return super.onCreateThumbnail(outBitmap, canvas);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return super.onGenericMotionEvent(event);
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent ev) {
        return super.dispatchGenericMotionEvent(ev);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        return super.dispatchKeyShortcutEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent ev) {
        return super.dispatchTrackballEvent(ev);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown called");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyLongPress called");
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyShortcut called");
        return super.onKeyShortcut(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyUp called");
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent called");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        return super.onTrackballEvent(event);
    }

    @Override
    public boolean onSupportNavigateUp() {
        boolean supportNavigateUp = super.onSupportNavigateUp();
        Log.d(TAG, "onSupportNavigateUp called supportNavigateUp?:" + supportNavigateUp);
        return supportNavigateUp;
    }

    @Override
    public boolean onNavigateUp() {
        boolean navigateUp = super.onNavigateUp();
        Log.d(TAG, "onNavigateUp called navigateUp:" + navigateUp);
        return navigateUp;
    }

    @Override
    public boolean onNavigateUpFromChild(Activity child) {
        return super.onNavigateUpFromChild(child);
    }

    @Override
    public void onCreateSupportNavigateUpTaskStack(@NonNull android.support.v4.app.TaskStackBuilder builder) {
        super.onCreateSupportNavigateUpTaskStack(builder);
    }

    @Override
    public void onPrepareSupportNavigateUpTaskStack(@NonNull android.support.v4.app.TaskStackBuilder builder) {
        super.onPrepareSupportNavigateUpTaskStack(builder);
    }

    @Override
    public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onCreateNavigateUpTaskStack(builder);
    }

    @Override
    public void onPrepareNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onPrepareNavigateUpTaskStack(builder);
    }

    @Nullable
    @Override
    public CharSequence onCreateDescription() {
        return super.onCreateDescription();
    }

    @Override
    public Uri onProvideReferrer() {
        return super.onProvideReferrer();
    }

    @Nullable
    @Override
    public View onCreatePanelView(int featureId) {
        return super.onCreatePanelView(featureId);
    }

    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
    }

    @Override
    public void onLocalVoiceInteractionStarted() {
        super.onLocalVoiceInteractionStarted();
    }

    @Override
    public void onLocalVoiceInteractionStopped() {
        super.onLocalVoiceInteractionStopped();
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
    }

    @Override
    public void onProvideAssistContent(AssistContent outContent) {
        super.onProvideAssistContent(outContent);
    }

    @Override
    public void onProvideAssistData(Bundle data) {
        super.onProvideAssistData(data);
    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, Menu menu, int deviceId) {
        super.onProvideKeyboardShortcuts(data, menu, deviceId);
    }

    @Override
    public void onVisibleBehindCanceled() {
        super.onVisibleBehindCanceled();
    }

    @Override
    public ComponentName getComponentName() {
        return super.getComponentName();
    }

    @Override
    public FragmentManager getFragmentManager() {
        return super.getFragmentManager();
    }

    @Override
    public int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    @Override
    public int getRequestedOrientation() {
        return super.getRequestedOrientation();
    }

    @Override
    public int getTaskId() {
        return super.getTaskId();
    }

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    /**
     * 用于创建一个延时响应的Intent，如通知栏消息点击之后跳转至某个activity。
     *
     * Service和广播接受者均有类似的方法
     * @param requestCode
     * @param data
     * @param flags
     * @return
     */
    @Override
    public PendingIntent createPendingResult(int requestCode, @NonNull Intent data, int flags) {
        return super.createPendingResult(requestCode, data, flags);
    }

    @Nullable
    @Override
    public Intent getParentActivityIntent() {
        return super.getParentActivityIntent();
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return super.getLayoutInflater();
    }

    /**
     * LoaderManager用来负责管理与Activity或者Fragment联系起来的一个或多个Loaders对象.
     * 每个Activity或者Fragment都有唯一的一个LoaderManager实例，用来启动、停止、保持、重启、
     * 关闭它的Loaders。这些事件有时直接在客户端通过调用initLoader()/restartLoader()/destroyLoader()
     * 函数来实现。google推荐使用Loader机制，来实现Provider的数据查询操作，注意LoaderManager与Activity
     * 或Fragment的生命周期相关联。
     *
     * @return
     */
    @Override
    public LoaderManager getLoaderManager() {
        return super.getLoaderManager();
    }

    @Override
    public Scene getContentScene() {
        return super.getContentScene();
    }

    @Override
    public SharedPreferences getPreferences(int mode) {
        return super.getPreferences(mode);
    }

    @NonNull
    @Override
    public String getLocalClassName() {
        return super.getLocalClassName();
    }

    @Override
    public TransitionManager getContentTransitionManager() {
        return super.getContentTransitionManager();
    }

    @Nullable
    @Override
    public View getCurrentFocus() {
        return super.getCurrentFocus();
    }

    @Override
    public Window getWindow() {
        return super.getWindow();
    }

    @Override
    public WindowManager getWindowManager() {
        return super.getWindowManager();
    }

    @Nullable
    @Override
    public android.support.v7.app.ActionBar getSupportActionBar() {
        return super.getSupportActionBar();
    }

    @NonNull
    @Override
    public AppCompatDelegate getDelegate() {
        return super.getDelegate();
    }

    @Nullable
    @Override
    public ActionBarDrawerToggle.Delegate getDrawerToggleDelegate() {
        return super.getDrawerToggleDelegate();
    }

    @Override
    public android.support.v4.app.FragmentManager getSupportFragmentManager() {
        return super.getSupportFragmentManager();
    }

    @Nullable
    @Override
    public Intent getSupportParentActivityIntent() {
        return super.getSupportParentActivityIntent();
    }

    @Override
    public android.support.v4.app.LoaderManager getSupportLoaderManager() {
        return super.getSupportLoaderManager();
    }

    @Override
    public MenuInflater getMenuInflater() {
        return super.getMenuInflater();
    }

    /******** Fragment 相关 ***********/

    /**
     * 调用时间为 fragment.onAttach --> Activity.onAttachFragment
     *
     * @param fragment
     */
    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.d(TAG, "onAttachFragment called fragment:" + fragment.getTag());
    }

    @Override
    public void onAttachFragment(android.support.v4.app.Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.d(TAG, "onAttachFragment v4 called fragment:" + fragment.getTag());
    }


    /**************** searchUI组件 ******************/

    /**
     * 用于浮动搜索的实现，即调用系统的searchUI组件。关键就是在该方法中启动search，即startSearch。
     *
     * @return
     */
    @Override
    public boolean onSearchRequested() {
        return super.onSearchRequested();
    }

    @Override
    public boolean onSearchRequested(@Nullable SearchEvent searchEvent) {
        return super.onSearchRequested(searchEvent);
    }

    @Override
    public void startSearch(@Nullable String initialQuery, boolean selectInitialQuery, @Nullable Bundle appSearchData, boolean globalSearch) {
        super.startSearch(initialQuery, selectInitialQuery, appSearchData, globalSearch);
    }

    /**
     * 触发搜索，类似于startSearch方法，用于触发Search
     *
     * @param query
     * @param appSearchData
     */
    @Override
    public void triggerSearch(String query, @Nullable Bundle appSearchData) {
        super.triggerSearch(query, appSearchData);
    }

    /************** activity的调用者信息 *******************/

    @Nullable
    @Override
    public String getCallingPackage() {
        return super.getCallingPackage();
    }

    @Nullable
    @Override
    public ComponentName getCallingActivity() {
        return super.getCallingActivity();
    }

    /**
     * 获取本activity的调用者信息
     *
     * @return
     */
    @Nullable
    @Override
    public Uri getReferrer() {
        return super.getReferrer();
    }
}
