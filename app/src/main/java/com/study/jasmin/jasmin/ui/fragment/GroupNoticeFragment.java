package com.study.jasmin.jasmin.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.activity.GroupReplyActivity;
import com.study.jasmin.jasmin.ui.list.AdaptInfoNoticeList;
import com.study.jasmin.jasmin.ui.list.ListInfoNotice;
import com.study.jasmin.jasmin.util.JasminPreference;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupNoticeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, AdaptInfoNoticeList.onButtonClickListener {
    public static final String TAG = "GroupNoticeFragment";
    private View noticeListHeader;
    private ImageView btnWrite;
    private ImageView btnFavorite;
    private OnFragmentSelectedListener fragmentListener;
    private ListView noticeList;
    private ArrayList<ListInfoNotice> arrayListInfo = new ArrayList<ListInfoNotice>();
    private AdaptInfoNoticeList adaptListInfo;

    public GroupNoticeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "GroupNoticeFragment onDetach");
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "GroupNoticeFragment onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "GroupNoticeFragment onResume");
        super.onResume();
    }

    @Override
    public void onStart() {
        Log.d(TAG, "GroupNoticeFragment onStart");
        super.onStart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "GroupNoticeFragment onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "GroupNoticeFragment onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group_notice, container, false);
        Log.d(TAG, "GroupNoticeFragment onCreateView");

        //Log.d(TAG, JasminPreference.getInstance(getContext()).getListValue("studyList").toString());

        findViews(rootView);
        initViews();

        // 0: No, 1: Title, 2: Views, 3: content, 4: date, 5: writer, 6: reply, 7: favorite
        ArrayList<String> NoticeColumnTest0 = new ArrayList<String>();
        NoticeColumnTest0.add("0");
        NoticeColumnTest0.add("연습글 가낟라마바사");
        NoticeColumnTest0.add("5");
        NoticeColumnTest0.add("동해물과 백두산이 마르고 닳도록...");
        NoticeColumnTest0.add("16.07.27");
        NoticeColumnTest0.add("홍길동");
        NoticeColumnTest0.add("8");
        NoticeColumnTest0.add("true");

        ArrayList<String> NoticeColumnTest1 = new ArrayList<String>();
        NoticeColumnTest1.add("1");
        NoticeColumnTest1.add("첫째주 숙제 입니다.");
        NoticeColumnTest1.add("2");
        NoticeColumnTest1.add("하느님이 보우하사 우리나라 만세 ");
        NoticeColumnTest1.add("16.07.28");
        NoticeColumnTest1.add("김길동");
        NoticeColumnTest1.add("2");
        NoticeColumnTest1.add("true");

        ArrayList<String> NoticeColumnTest2 = new ArrayList<String>();
        NoticeColumnTest2.add("2");
        NoticeColumnTest2.add("긴급공지 입니다.");
        NoticeColumnTest2.add("12");
        NoticeColumnTest2.add("무궁화 삼천리 화려강산 대한사람... ");
        NoticeColumnTest2.add("16.07.31");
        NoticeColumnTest2.add("운영자");
        NoticeColumnTest2.add("9");
        NoticeColumnTest2.add("true");

        ArrayList<String> NoticeColumnTest3 = new ArrayList<String>();
        NoticeColumnTest3.add("3");
        NoticeColumnTest3.add("abcfemkc아리ㅓㄷ");
        NoticeColumnTest3.add("11");
        NoticeColumnTest3.add("대한으로 길이 보전하세 ");
        NoticeColumnTest3.add("16.08.02");
        NoticeColumnTest3.add("이승철");
        NoticeColumnTest3.add("9");
        NoticeColumnTest3.add("true");

        ArrayList<String> NoticeColumnTest4 = new ArrayList<String>();
        NoticeColumnTest4.add("4");
        NoticeColumnTest4.add("테스테스테스테스트");
        NoticeColumnTest4.add("100");
        NoticeColumnTest4.add("대한으로 길이 보전하세 ");
        NoticeColumnTest4.add("15.02.02");
        NoticeColumnTest4.add("아줌마");
        NoticeColumnTest4.add("5");
        NoticeColumnTest4.add("true");

        ArrayList<String> NoticeColumnTest5 = new ArrayList<String>();
        NoticeColumnTest5.add("3");
        NoticeColumnTest5.add("TestTestTest");
        NoticeColumnTest5.add("11");
        NoticeColumnTest5.add("대한으로 길이 보전하세 ");
        NoticeColumnTest5.add("14.11.02");
        NoticeColumnTest5.add("아줌마");
        NoticeColumnTest5.add("9");
        NoticeColumnTest5.add("true");

        ArrayList<ArrayList<String>> NoticeRowTest = new ArrayList<ArrayList<String>>();

        NoticeRowTest.add(NoticeColumnTest0);
        NoticeRowTest.add(NoticeColumnTest1);
        NoticeRowTest.add(NoticeColumnTest2);
        NoticeRowTest.add(NoticeColumnTest3);
        NoticeRowTest.add(NoticeColumnTest4);
        NoticeRowTest.add(NoticeColumnTest5);

        adaptListInfo = new AdaptInfoNoticeList(getContext(), R.layout.list_notice_info, arrayListInfo);
        adaptListInfo.setOnButtonClickListener(this);
        noticeList.setAdapter(adaptListInfo);
        addSelectInfo(NoticeRowTest);
        adaptListInfo.setArraySelectInfo(arrayListInfo);
        noticeList.setAdapter(adaptListInfo);
        return rootView;
    }

    public void addSelectInfo(ArrayList<ArrayList<String>> parseredList) {
        // 0: No, 1: Title, 2: Views, 3: content, 4: date, 5: writer, 6: reply, 7: favorite
        for (int i = 0; i < parseredList.size(); i++) {
            ListInfoNotice selectInfo = new ListInfoNotice();
            selectInfo.setNo(parseredList.get(i).get(0));
            selectInfo.setTitle(parseredList.get(i).get(1));
            selectInfo.setViews(parseredList.get(i).get(2));
//            selectInfo.setContent(parseredList.get(i).get(3)); // Todo: 이미지 있는 컨텐츠 고려 작성
            selectInfo.setDate(parseredList.get(i).get(4));
            selectInfo.setWriter(parseredList.get(i).get(5));
            selectInfo.setReply(parseredList.get(i).get(6));
//            selectInfo.setFavorite(parseredList.get(i).get(7)); // Todo: 이미지 버튼 아직 안함..

            arrayListInfo.add(selectInfo);
        }
    }



    private void findViews(View rootView) {

        noticeList = (ListView) rootView.findViewById(R.id.list_notice);
        noticeListHeader = rootView.findViewById(R.id.include);
        btnWrite = (ImageView) noticeListHeader.findViewById(R.id.notice_group_write);
        btnFavorite = (ImageView) noticeList.findViewById(R.id.notice_favorite);

    }

    private void initViews() {
        noticeList.setOnItemClickListener(this);
        btnWrite.setOnClickListener(this);
    }


    public interface OnFragmentSelectedListener {
        public void onFragmentSelected(View v); // 글쓰기버튼용
        public void onFragmentSelected(int position); // 리스트 아이템 클릭용
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "GroupNoticeFragment onAttach");
        super.onAttach(context);
        try {
            fragmentListener = (OnFragmentSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentSelectedListener");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notice_group_write:
                Log.d(TAG,"click notice_group_write");
                fragmentListener.onFragmentSelected(v);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "click onFavoriteState position : "+ position + ", id : " + id);
        fragmentListener.onFragmentSelected(position);
    }

    @Override
    public void onFavoriteState(ImageView v, boolean favorite) {
        Log.d(TAG, "click onFavoriteState v : ");
        if(favorite) {
            v.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            v.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }

    }


    @Override
    public void onAddReply() {
        Log.d(TAG, "click onAddReply");
        startActivity(new Intent(getContext(), GroupReplyActivity.class));
    }
}
