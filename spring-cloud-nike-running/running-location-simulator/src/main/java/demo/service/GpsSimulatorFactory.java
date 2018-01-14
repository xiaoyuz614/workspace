package demo.service;

import demo.model.GpsSimulatorRequest;
import demo.model.Point;
import demo.task.LocationSimulator;

import java.util.List;
//通过factory生成GPS simulator
public interface GpsSimulatorFactory {
    //有一个request 返回一个LocationSimulator
    //相当于启动一个线程来去做simulation
    //做一个异步线程
    //起一组线程 不会导致等待
    //间隔一段时间发一个request
    LocationSimulator prepareGpsSimulator(GpsSimulatorRequest gpsSimulatorRequest);

    LocationSimulator prepareGpsSimulator(LocationSimulator locationSimulator, List<Point> points);
}
