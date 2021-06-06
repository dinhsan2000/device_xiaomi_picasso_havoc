package org.lineageos.settings;

import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.widget.Toast;
import androidx.preference.PreferenceFragment;
import androidx.preference.Preference;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import androidx.preference.SwitchPreference;

import org.lineageos.settings.utils.RefreshRateUtils;

public class DevicePreferenceFragment extends PreferenceFragment {
    private static final String OVERLAY_NO_FILL_PACKAGE = "org.lineageos.overlay.notch.nofill";

@@ -39,6 +40,7 @@
    private ListPreference mPrefMinRefreshRate;
    private SwitchPreference mPrefPillStyleNotch;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
@@ -58,46 +60,38 @@ public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    
    @Override
    public void onResume() {
        super.onResume();
        updateValuesAndSummaries();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPrefMinRefreshRate.setValueIndex(RefreshRateUtils.getRefreshRate(getActivity()));
        mPrefMinRefreshRate.setSummary(mPrefMinRefreshRate.getEntry());
        try {
            mPrefPillStyleNotch.setChecked(
                    !mOverlayService.getOverlayInfo(OVERLAY_NO_FILL_PACKAGE, 0).isEnabled());
        } catch (RemoteException e) {
            // We can do nothing
        }
    }

    private final Preference.OnPreferenceChangeListener PrefListener =
    new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            final String key = preference.getKey();

            if (KEY_MIN_REFRESH_RATE.equals(key)) {
                RefreshRateUtils.setRefreshRate(getActivity(), Integer.parseInt((String) value));
                RefreshRateUtils.setFPS(Integer.parseInt((String) value));
                mPrefMinRefreshRate.setValueIndex(Integer.parseInt((String) value));
                mPrefMinRefreshRate.setSummary(mPrefMinRefreshRate.getEntry());
            } else if (KEY_PILL_STYLE_NOTCH.equals(key)) {
                try {
                    mOverlayService.setEnabled(
                            OVERLAY_NO_FILL_PACKAGE, !(boolean) value, 0);
                } catch (RemoteException e) {
                    // We can do nothing
                }
                Toast.makeText(getContext(),
                        R.string.msg_device_need_restart, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };
}