package com.chenwen.demo.student;

public class ProductionProcess {
    private final Integer id;
    private final String processName;
    private final Integer workerId;
    private final Integer workerPosition;
    private final String device;
    private final Integer standardTimeCost;
    private final Integer childStandardTimeCost;
    private final Integer realTimeCost;
    private final Integer isDivided; // 0 false, 1 true
    private final Integer childIndex; // index from 0
    private final Integer childLength; // e.g. 2 then someProcess become someProcess1 and someProcess2
    private final Integer beat;
    private final Integer timeDifference; // timeDifference = standardTimeCost - childStandardTimeCost
// (0, 'someProcess', 0, 0, 'someDevice', 220, 0, 0, 0, 0, 0, 250, 30)


    public ProductionProcess(Integer id, String processName, Integer workerId, Integer workerPosition, String device, Integer standardTimeCost, Integer childStandardTimeCost, Integer realTimeCost, Integer isDivided, Integer childIndex, Integer childLength, Integer beat, Integer timeDifference) {
        this.id = id;
        this.processName = processName;
        this.workerId = workerId;
        this.workerPosition = workerPosition;
        this.device = device;
        this.standardTimeCost = standardTimeCost;
        this.childStandardTimeCost = childStandardTimeCost;
        this.realTimeCost = realTimeCost;
        this.isDivided = isDivided;
        this.childIndex = childIndex;
        this.childLength = childLength;
        this.beat = beat;
        this.timeDifference = timeDifference;
    }

    public Integer getId() {
        return id;
    }

    public String getProcessName() {
        return processName;
    }

    public Integer getWorkerPosition() {
        return workerPosition;
    }

    public String getDevice() {
        return device;
    }

    public Integer getStandardTimeCost() {
        return standardTimeCost;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public Integer getChildIndex() {
        return childIndex;
    }

    public Integer getRealTimeCost() {
        return realTimeCost;
    }

    public Integer getIsDivided() {
        return isDivided;
    }

    public Integer getChildLength() {
        return childLength;
    }

    public Integer getChildStandardTimeCost() {
        return childStandardTimeCost;
    }

    public Integer getBeat() {
        return beat;
    }

    public Integer getTimeDifference() {
        return timeDifference;
    }
}
