package com.example.vehicle_networking.thread;

import com.example.vehicle_networking.form.ReadDataParaForm;
import com.example.vehicle_networking.service.DataCollectionService;
import com.example.vehicle_networking.utils.GetBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.context.SpringContextResourceAdapter;
import org.springframework.stereotype.Service;

/**
 * @author ：GO FOR IT
 * @description：
 * @date ：2021/9/8 20:53
 */
@Slf4j
public class ReadDataThread extends Thread{

    private volatile boolean flag = false;

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

    public ReadDataThread(String name,ReadDataParaForm readDataParaForm){
        super(name);
        this.readDataParaForm = readDataParaForm;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                while (flag) {
                    DataCollectionService dataCollectionService = (DataCollectionService) GetBeanUtil.getBean("dataCollectionServiceImpl");
                    dataCollectionService.getSpeedFromURL(readDataParaForm.getUrl(), readDataParaForm.getCookie(), readDataParaForm.getVehicleId());
                    log.info(" 线程 {} 保存数据成功", Thread.currentThread().getName());
                    Thread.sleep(3000);
                }
            }catch (InterruptedException ie) {
                System.out.println(Thread.currentThread().getName() +" ("+this.getState()+") catch InterruptedException.");
            }
        }
    }
}
