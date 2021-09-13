package com.example.vehicle_networking.thread;

import com.example.vehicle_networking.config.CollectDataThreadConfig;
import com.example.vehicle_networking.form.ReadDataParaForm;
import com.example.vehicle_networking.mapper.VehicleMapper;
import com.example.vehicle_networking.service.DataCollectionService;
import com.example.vehicle_networking.utils.GetBeanUtil;
import com.example.vehicle_networking.utils.ReadDataAPI;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * @author ：GO FOR IT
 * @description：
 * @date ：2021/9/8 20:53
 */
@Slf4j
public class ReadDataThread extends Thread{

    private volatile boolean flag = true;

    public void stopTask() {
        flag = false;
    }

    public void startTask() {
        flag = true;
    }
    public boolean getStatus(){
        return flag;
    }

    private ReadDataParaForm readDataParaForm;

    public ReadDataThread(String name){
        super(name);
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setReadDataParaForm(ReadDataParaForm readDataParaForm) {
        this.readDataParaForm = readDataParaForm;
    }

    public ReadDataThread(String name, ReadDataParaForm readDataParaForm){
        super(name);
        this.readDataParaForm = readDataParaForm;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                while (flag) {
                    DataCollectionService dataCollectionService = (DataCollectionService) GetBeanUtil.getBean("dataCollectionServiceImpl");
                    CollectDataThreadConfig collectDataThreadConfig = (CollectDataThreadConfig) GetBeanUtil.getBean("collectDataThreadConfig");
                    Set<Integer> vehicleIds = collectDataThreadConfig.getReadDataThreadMap().keySet();
                    for (Integer vehicleId : vehicleIds) {
                        dataCollectionService.getSpeedFromURL(readDataParaForm.getUrl(), readDataParaForm.getCookie(), vehicleId,readDataParaForm.getVehicleName());
                        log.info(" 线程 {} 保存数据成功", getName());
                    }
                    int interval = readDataParaForm.getInterval();
                    Thread.sleep( interval > 0 ? interval * 1000 : 3000);
                }
                log.info("线程 {} 停止采集数据", getName());
            }catch (InterruptedException ie) {
                System.out.println(Thread.currentThread().getName() +" ("+this.getState()+") catch InterruptedException.");
            }
        }
    }
}
