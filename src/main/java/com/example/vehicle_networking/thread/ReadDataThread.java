package com.example.vehicle_networking.thread;

import com.example.vehicle_networking.form.ReadDataParaForm;
import com.example.vehicle_networking.service.DataCollectionService;
import com.example.vehicle_networking.utils.GetBeanUtil;
import lombok.extern.slf4j.Slf4j;
/**
 * @author ：GO FOR IT
 * @description：
 * @date ：2021/9/8 20:53
 */
@Slf4j
public class ReadDataThread extends Thread{

    private static volatile boolean flag = false;

    public static void stopTask() {
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

    public static void setFlag(boolean flag) {
        ReadDataThread.flag = flag;
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
                    dataCollectionService.getSpeedFromURL(readDataParaForm.getUrl(), readDataParaForm.getCookie(), readDataParaForm.getVehicleId());
                    log.info(" 线程 {} 保存数据成功", Thread.currentThread().getName());
                    int interval = readDataParaForm.getInterval();
                    Thread.sleep( interval > 0 ? interval * 1000 : 3000);
                }
                log.info("线程 {} 停止", Thread.currentThread().getName());
            }catch (InterruptedException ie) {
                System.out.println(Thread.currentThread().getName() +" ("+this.getState()+") catch InterruptedException.");
            }
        }
    }
}
