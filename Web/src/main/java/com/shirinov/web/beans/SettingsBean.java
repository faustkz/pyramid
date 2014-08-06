package com.shirinov.web.beans;

import com.shirinov.web.entities.Setting;
import com.shirinov.web.entities.dao.AbstractDao;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Rustam Shirinov
 * Date: 14/07/14
 * Time: 11:39 PM
 */
@ManagedBean /*(name="settingsBean", eager=true)*/
@ApplicationScoped
public class SettingsBean implements Serializable {

    private static final long SerialVesrionUID = 103L;

    private static HashMap<String, String> settings = new HashMap<>();


    public String getSetting(String name){
        if (settings==null || settings.size()==0){
            AbstractDao<Setting> settingDao = new AbstractDao<>();
            List<Setting> settingsList = new ArrayList<>();
            settingsList = settingDao.get(Setting.QUERY_GET_ALL);
            for(Setting set : settingsList){
                settings.put(set.getName(),set.getValue());
            }
        }
        return (settings.containsKey(name))? settings.get(name):null;
    }

}
