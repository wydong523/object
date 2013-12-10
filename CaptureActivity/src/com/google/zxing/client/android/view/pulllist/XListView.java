package com.google.zxing.client.android.view.pulllist;

import com.google.zxing.client.android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * 自定义List
 * 
 * @author 汪渝栋
 * 
 */
public class XListView extends ListView implements OnScrollListener {

	private float mLastY = -1;
	private Scroller mScroller;
	private OnScrollListener mScrollListener;
	private IXListViewListener mListViewListener;
	private XListViewHeader mHeaderView;
	private RelativeLayout mHeaderViewContent;
	private TextView mHeaderTimeView;
	private int mHeaderViewHeight;
	private boolean mEnablePullRefresh = true;
	private boolean mPullRefreshing = false;
	private XListViewFooter mFooterView;
	private boolean mEnablePullLoad;
	private boolean mPullLoading;
	private boolean mIsFooterReady = false;
	private int mTotalItemCount;
	private int mScrollBack;
	private final static int SCROLLBACK_HEADER = 0;
	private final static int SCROLLBACK_FOOTER = 1;
	private final static int SCROLL_DURATION = 400;
	private final static int PULL_LOAD_MORE_DELTA = 50;
	private final static float OFFSET_RADIO = 1.8f;
	private int heightPixels = 0;// 屏幕高度

	/**
	 * @param context
	 */
	public XListView(Context context) {
		super(context);
		initWithContext(context);
	}

	public XListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initWithContext(context);
	}

	public XListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initWithContext(context);
	}

	private void initWithContext(Context context) {
		// 获取屏幕实际分辨率的高度修改当列表实体数没有填满情况下，下拉的bug
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		heightPixels = dm.heightPixels;
		System.out.println("heightPixels is" + heightPixels);
		mScroller = new Scroller(context, new DecelerateInterpolator());
		super.setOnScrollListener(this);
		mHeaderView = new XListViewHeader(context);
		mHeaderViewContent = (RelativeLayout) mHeaderView
				.findViewById(R.id.xlistview_header_content);
		mHeaderTimeView = (TextView) mHeaderView
				.findViewById(R.id.xlistview_header_time);
		addHeaderView(mHeaderView);
		mFooterView = new XListViewFooter(context);
		mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						mHeaderViewHeight = mHeaderViewContent.getHeight();
						getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
					}
				});
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		if (mIsFooterReady == false) {
			mIsFooterReady = true;
			addFooterView(mFooterView);
		}
		super.setAdapter(adapter);
	}

	public void setPullRefreshEnable(boolean enable) {
		mEnablePullRefresh = enable;
		if (!mEnablePullRefresh) { // disable, hide the content
			mHeaderViewContent.setVisibility(View.INVISIBLE);
		} else {
			mHeaderViewContent.setVisibility(View.VISIBLE);
		}
	}

	public void setPullLoadEnable(boolean enable) {
		mEnablePullLoad = enable;
		if (!mEnablePullLoad) {
			mFooterView.hide();
			mFooterView.setOnClickListener(null);
		} else {
			mPullLoading = false;
			mFooterView.show();
			mFooterView.setState(XListViewFooter.STATE_NORMAL);
			// both "pull up" and "click" will invoke load more.
			mFooterView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					startLoadMore();
				}
			});
		}
	}

	/**
	 * 停止刷新，重置头�?
	 */
	public void stopRefresh() {
		if (mPullRefreshing == true) {
			mPullRefreshing = false;
			resetHeaderHeight();
		}
	}

	/**
	 * 停止加载更多初始化底�?
	 */
	public void stopLoadMore() {
		if (mPullLoading == true) {
			mPullLoading = false;
			mFooterView.setState(XListViewFooter.STATE_NORMAL);
		}
	}

	/**
	 * set last refresh time
	 * 
	 * @param time
	 */
	public void setRefreshTime(String time) {
		mHeaderTimeView.setText(time);
	}

	private void invokeOnScrolling() {
		if (mScrollListener instanceof OnXScrollListener) {
			OnXScrollListener l = (OnXScrollListener) mScrollListener;
			l.onXScrolling(this);
		}
	}

	private void updateHeaderHeight(float delta) {
		mHeaderView.setVisiableHeight((int) delta
				+ mHeaderView.getVisiableHeight());
		if (mEnablePullRefresh && !mPullRefreshing) { // 未处于刷新状态，更新箭头
			if (mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
				mHeaderView.setState(XListViewHeader.STATE_READY);
			} else {
				mHeaderView.setState(XListViewHeader.STATE_NORMAL);
			}
		}
		setSelection(0);
	}

	private void resetHeaderHeight() {
		int height = mHeaderView.getVisiableHeight();
		if (height == 0)
			return;
		if (mPullRefreshing && height <= mHeaderViewHeight) {
			return;
		}
		int finalHeight = 0;
		if (mPullRefreshing && height > mHeaderViewHeight) {
			finalHeight = mHeaderViewHeight;
		}
		mScrollBack = SCROLLBACK_HEADER;
		mScroller.startScroll(0, height, 0, finalHeight - height,
				SCROLL_DURATION);
		invalidate();
	}

	private void updateFooterHeight(int delta) {

		// 修改上拉bug
		// int height = mFooterView.getBottomMargin() + (int) delta;
		int height = delta;
		if (mEnablePullLoad && !mPullLoading) {
			if (height < PULL_LOAD_MORE_DELTA) {
				mFooterView.setState(XListViewFooter.STATE_READY);
			} else {
				mFooterView.setState(XListViewFooter.STATE_NORMAL);
			}
		}
		// mFooterView.setBottomMargin(height);

		// setSelection(mTotalItemCount - 1);
	}

	private void resetFooterHeight(int hight) {
		int bottomMargin = mFooterView.getBottomMargin();
		if (hight < 0) {
			mScrollBack = SCROLLBACK_FOOTER;
			mScroller.startScroll(0, -200, 0, 200, SCROLL_DURATION);
			invalidate();
		}
	}

	private void startLoadMore() {
		mPullLoading = true;
		mFooterView.setState(XListViewFooter.STATE_LOADING);
		if (mListViewListener != null) {
			mListViewListener.onLoadMore();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (mLastY == -1) {
			mLastY = ev.getRawY();
		}

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastY = ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			final float deltaY = ev.getRawY() - mLastY;
			mLastY = ev.getRawY();
			if (getFirstVisiblePosition() == 0
					&& (mHeaderView.getVisiableHeight() > 0 || deltaY > 0)) {
				updateHeaderHeight(deltaY / OFFSET_RADIO);
				invokeOnScrolling();
			} else if (getLastVisiblePosition() == mTotalItemCount - 1
					&& (mFooterView.getBottom() - heightPixels < 0)) {
				updateFooterHeight(heightPixels - mFooterView.getBottom());
			}
			// 修改下拉bug 293-297
			// else if (getLastVisiblePosition() == mTotalItemCount - 1
			// && (mFooterView.getBottomMargin() > 0 && deltaY < 0)) {
			// // last item, already pulled up or want to pull up.
			// updateFooterHeight(-deltaY / OFFSET_RADIO);
			// }
			break;
		default:
			// 重置y轴位�? mLastY = -1;
			if (getFirstVisiblePosition() == 0) {
				// 上拉刷新数据
				if (mEnablePullRefresh
						&& mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
					mPullRefreshing = true;
					mHeaderView.setState(XListViewHeader.STATE_REFRESHING);
					if (mListViewListener != null) {
						mListViewListener.onRefresh();
					}
				}
				resetHeaderHeight();
			} else if (getLastVisiblePosition() == mTotalItemCount - 1) {
				// 上拉加载数据
				// if (mEnablePullLoad
				// && mFooterView.getBottomMargin() > PULL_LOAD_MORE_DELTA) {
				// 修改上拉加载bug
				if (mEnablePullLoad
						&& (mFooterView.getBottom() - heightPixels < 0)) {
					startLoadMore();
				}
				resetFooterHeight(mFooterView.getBottom() - heightPixels);
			}
			break;
		}
		return super.onTouchEvent(ev);
	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			if (mScrollBack == SCROLLBACK_HEADER) {
				mHeaderView.setVisiableHeight(mScroller.getCurrY());
			} else {
				mFooterView.setBottomMargin(mScroller.getCurrY());
			}
			postInvalidate();
			invokeOnScrolling();
		}
		super.computeScroll();
	}

	@Override
	public void setOnScrollListener(OnScrollListener l) {
		mScrollListener = l;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (mScrollListener != null) {
			mScrollListener.onScrollStateChanged(view, scrollState);
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// send to user's listener
		mTotalItemCount = totalItemCount;
		if (mScrollListener != null) {
			mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount,
					totalItemCount);
		}
	}

	public void setXListViewListener(IXListViewListener l) {
		mListViewListener = l;
	}

	public interface OnXScrollListener extends OnScrollListener {
		public void onXScrolling(View view);
	}

	/**
	 * 实现刷新/加载
	 */
	public interface IXListViewListener {
		public void onRefresh();

		public void onLoadMore();
	}
}
