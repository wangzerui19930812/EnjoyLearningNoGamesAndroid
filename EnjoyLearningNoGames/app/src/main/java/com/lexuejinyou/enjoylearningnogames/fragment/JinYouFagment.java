package com.lexuejinyou.enjoylearningnogames.fragment;

import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lexuejinyou.enjoylearningnogames.R;
import com.lexuejinyou.enjoylearningnogames.base.BaseFragment;
import com.lexuejinyou.enjoylearningnogames.model.SortModel;
import com.lexuejinyou.enjoylearningnogames.util.AppUtils;
import com.lexuejinyou.enjoylearningnogames.view.adapter.SortAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JinYouFagment extends BaseFragment {

    private TextView textView;

    private ListView listView;

    private Context context;

    private List<SortModel> sortModelList = new ArrayList<>();

    @Override
    protected View initView() {
        System.out.println("JinYouFagment initView");
        context = getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.jinyou, null);
    }

    @Override
    protected void initData() {
        super.initData();
        ListView listView = getActivity().findViewById(R.id.list_view_user_list);
        List<ResolveInfo> resolveInfos = AppUtils.queryAllApp(context);
        PackageManager packageManager = context.getPackageManager();
        for (ResolveInfo resolveInfo : resolveInfos) {
            String packageName = resolveInfo.activityInfo.packageName;//包名
            Drawable icon = resolveInfo.loadIcon(packageManager);//APP图标 icon
            String appName = resolveInfo.loadLabel(packageManager).toString();//APP名字
            String name = resolveInfo.activityInfo.name;

            if(appName.equals("androiddemo01")){
                System.out.println(packageName);
//                 packageManager.setApplicationEnabledSetting(packageName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, 0);
//               packageManager.setApplicationEnabledSetting(packageName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER, 0);
//                packageManager.setApplicationEnabledSetting(packageName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED, 0);
            }


            SortModel sortModel = new SortModel();
            sortModel.setId("1");
            sortModel.setInfo("状态 时间");
            sortModel.setName(appName);
            sortModel.setSortLetters("4");
            sortModel.setIcon(icon);
            sortModelList.add(sortModel);
        }
        SortAdapter adapter = new SortAdapter(context, sortModelList);
        listView.setAdapter(adapter);

    }

    private List<String> getPkgListNew() {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = getContext().getPackageManager();
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL);

        for (ResolveInfo resolveInfo : resolveInfos) {
            String packageName = resolveInfo.activityInfo.packageName;//包名
            Drawable appicon = resolveInfo.loadIcon(packageManager);//APP图标 icon
            String appName = resolveInfo.loadLabel(packageManager).toString();//APP名字
            String name = resolveInfo.activityInfo.name;
        }


        UsageStatsManager usageStatsManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            usageStatsManager = (UsageStatsManager) getContext().getSystemService(Context.USAGE_STATS_SERVICE);
        }


        List<String> packages = new ArrayList<String>();
        try {
            List<PackageInfo> packageInfos = getContext().getPackageManager().getInstalledPackages(0);
//            List<ResolveInfo> resolveInfos = getContext().getPackageManager().queryIntentActivities(new Intent(getActivity(), Intent.class), PackageManager.MATCH_ALL);
//            System.out.println(resolveInfos);
            PackageManager pm = getContext().getPackageManager();
//            List<ApplicationInfo> infos = pm.getInstalledApplications(PackageManager.MATCH_UNINSTALLED_PACKAGES);
            List<ApplicationInfo> infos = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
            for (PackageInfo info : packageInfos) {
                String pkg = info.packageName;
                if(!isSystemApp(info)){
                    System.out.println("1");
                }
                packages.add(pkg);
            }
        } catch (Throwable t) {
            t.printStackTrace();;
        }
        return packages;
    }



    private List<String> getPkgList() {
        List<String> packages = new ArrayList<String>();
        try {
            Process p = Runtime.getRuntime().exec("pm list packages");
            InputStreamReader isr = new InputStreamReader(p.getInputStream(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null) {
                line = line.trim();
                if (line.length() > 8) {
                    String prefix = line.substring(0, 8);
                    if (prefix.equalsIgnoreCase("package:")) {
                        line = line.substring(8).trim();
                        if (!TextUtils.isEmpty(line)) {
                            packages.add(line);
                        }
                    }
                }
                line = br.readLine();
            }
            br.close();
            p.destroy();
        } catch (Throwable t) {

        }
        return packages;
    }

    /**
     * 通过packName得到PackageInfo，作为参数传入即可
     * @param pi
     * @return
     */
    private boolean isSystemApp(PackageInfo pi) {
        if(pi.applicationInfo.name.contains("Chrome")){
            System.out.println("Chrome");
        }
        boolean isSysApp = (pi.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1;
        boolean isSysUpd = (pi.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) == 1;
        return isSysApp || isSysUpd;
    }
}
