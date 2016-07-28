package com.study.jasmin.jasmin.ui.fragment;


import android.content.Context;
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
import android.widget.Toast;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.list.AdaptInfoNoticeList;
import com.study.jasmin.jasmin.ui.list.ListInfoNotice;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupNoticeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static final String TAG = "GroupNoticeFragment";
    private View noticeListHeader;
    private ImageView btnWrite;
    private ImageView btnOrderLiteral;
    private ImageView btnOrderNumber;
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

        findViews(rootView);
        initViews();

        ArrayList<String> NoticeColumnTest0 = new ArrayList<String>();
        NoticeColumnTest0.add("No0");
        NoticeColumnTest0.add("제목0");
        NoticeColumnTest0.add("작성일0");
        NoticeColumnTest0.add("작성자0");

        ArrayList<String> NoticeColumnTest1 = new ArrayList<String>();
        NoticeColumnTest1.add("No1");
        NoticeColumnTest1.add("제목1");
        NoticeColumnTest1.add("작성일1");
        NoticeColumnTest1.add("작성자1");

        ArrayList<String> NoticeColumnTest2 = new ArrayList<String>();
        NoticeColumnTest2.add("No2");
        NoticeColumnTest2.add("제목2");
        NoticeColumnTest2.add("작성일2");
        NoticeColumnTest2.add("작성자2");

        ArrayList<String> NoticeColumnTest3 = new ArrayList<String>();
        NoticeColumnTest3.add("No2");
        NoticeColumnTest3.add("제목2");
        NoticeColumnTest3.add("작성일2");
        NoticeColumnTest3.add("작성자2");

        ArrayList<String> NoticeColumnTest4 = new ArrayList<String>();
        NoticeColumnTest4.add("No2");
        NoticeColumnTest4.add("제목2");
        NoticeColumnTest4.add("작성일2");
        NoticeColumnTest4.add("작성자2");

        ArrayList<String> NoticeColumnTest5 = new ArrayList<String>();
        NoticeColumnTest5.add("No2");
        NoticeColumnTest5.add("제목2");
        NoticeColumnTest5.add("작성일2");
        NoticeColumnTest5.add("작성자2");

        ArrayList<String> NoticeColumnTest6 = new ArrayList<String>();
        NoticeColumnTest6.add("No2");
        NoticeColumnTest6.add("제목2");
        NoticeColumnTest6.add("작성일2");
        NoticeColumnTest6.add("작성자2");

        ArrayList<String> NoticeColumnTest7 = new ArrayList<String>();
        NoticeColumnTest7.add("No2");
        NoticeColumnTest7.add("제목2");
        NoticeColumnTest7.add("작성일2");
        NoticeColumnTest7.add("작성자2");

        ArrayList<String> NoticeColumnTest8 = new ArrayList<String>();
        NoticeColumnTest8.add("No2");
        NoticeColumnTest8.add("제목2");
        NoticeColumnTest8.add("작성일2");
        NoticeColumnTest8.add("작성자2");

        ArrayList<String> NoticeColumnTest9 = new ArrayList<String>();
        NoticeColumnTest9.add("No2");
        NoticeColumnTest9.add("제목2");
        NoticeColumnTest9.add("작성일2");
        NoticeColumnTest9.add("작성자2");

        ArrayList<String> NoticeColumnTest10 = new ArrayList<String>();
        NoticeColumnTest10.add("No2");
        NoticeColumnTest10.add("제목2");
        NoticeColumnTest10.add("작성일2");
        NoticeColumnTest10.add("작성자2");

        ArrayList<String> NoticeColumnTest11 = new ArrayList<String>();
        NoticeColumnTest11.add("No2");
        NoticeColumnTest11.add("제목2");
        NoticeColumnTest11.add("작성일2");
        NoticeColumnTest11.add("작성자2");

        ArrayList<String> NoticeColumnTest12 = new ArrayList<String>();
        NoticeColumnTest12.add("No2");
        NoticeColumnTest12.add("제목2");
        NoticeColumnTest12.add("작성일2");
        NoticeColumnTest12.add("작성자2");

        ArrayList<String> NoticeColumnTest13 = new ArrayList<String>();
        NoticeColumnTest13.add("No2");
        NoticeColumnTest13.add("제목2");
        NoticeColumnTest13.add("작성일2");
        NoticeColumnTest13.add("작성자2");

        ArrayList<ArrayList<String>> NoticeRowTest = new ArrayList<ArrayList<String>>();

        NoticeRowTest.add(NoticeColumnTest0);
        NoticeRowTest.add(NoticeColumnTest1);
        NoticeRowTest.add(NoticeColumnTest2);
        NoticeRowTest.add(NoticeColumnTest3);
        NoticeRowTest.add(NoticeColumnTest4);
        NoticeRowTest.add(NoticeColumnTest5);
        NoticeRowTest.add(NoticeColumnTest6);
        NoticeRowTest.add(NoticeColumnTest7);
        NoticeRowTest.add(NoticeColumnTest8);
        NoticeRowTest.add(NoticeColumnTest9);
        NoticeRowTest.add(NoticeColumnTest10);
        NoticeRowTest.add(NoticeColumnTest11);
        NoticeRowTest.add(NoticeColumnTest12);
        NoticeRowTest.add(NoticeColumnTest13);

        adaptListInfo = new AdaptInfoNoticeList(getContext(), R.layout.list_notice_info, arrayListInfo);
        noticeList.setAdapter(adaptListInfo);
        addSelectInfo(NoticeRowTest);
        adaptListInfo.setArraySelectInfo(arrayListInfo);
        noticeList.setAdapter(adaptListInfo);
        return rootView;
    }

    public void addSelectInfo(ArrayList<ArrayList<String>> parseredList) {
        for (int i = 0; i < parseredList.size(); i++) {
            ListInfoNotice selectInfo = new ListInfoNotice();
            selectInfo.setNo(parseredList.get(i).get(0));
            selectInfo.setTitle(parseredList.get(i).get(1));
            selectInfo.setDate(parseredList.get(i).get(2));
            selectInfo.setWriter(parseredList.get(i).get(3));

            arrayListInfo.add(selectInfo);
        }
    }



    private void findViews(View rootView) {

        noticeList = (ListView) rootView.findViewById(R.id.list_notice);
        noticeListHeader = rootView.findViewById(R.id.include);
        btnWrite = (ImageView) noticeListHeader.findViewById(R.id.notice_group_write);
        btnOrderLiteral= (ImageView) noticeListHeader.findViewById(R.id.notice_group_order_literal);
        btnOrderNumber = (ImageView) noticeListHeader.findViewById(R.id.notice_group_order_number);

    }

    private void initViews() {
        noticeList.setOnItemClickListener(this);
        btnWrite.setOnClickListener(this);
        btnOrderLiteral.setOnClickListener(this);
        btnOrderNumber.setOnClickListener(this);
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

            case R.id.notice_group_order_literal:
                Log.d(TAG,"click notice_group_order_literal");
                break;

            case R.id.notice_group_order_number:
                Log.d(TAG,"click notice_group_order_number");
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(),""+ position,Toast.LENGTH_SHORT).show();
        fragmentListener.onFragmentSelected(position);
    }
}
