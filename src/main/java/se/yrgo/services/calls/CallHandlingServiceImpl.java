package se.yrgo.services.calls;

import se.yrgo.domain.Action;
import se.yrgo.domain.Call;

import java.util.*;

import se.yrgo.services.customers.*;
import se.yrgo.services.diary.DiaryManagementService;

public class CallHandlingServiceImpl implements CallHandlingService {
    private final DiaryManagementService diaryManagementService;
    private final CustomerManagementService customerManagementService;
    CallHandlingServiceImpl(CustomerManagementService customerManagementService, DiaryManagementService diaryManagementService) {
        this.customerManagementService = customerManagementService;
        this.diaryManagementService = diaryManagementService;
    }
    @Override
    public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException{
        customerManagementService.recordCall(customerId, newCall);
        for(Action action : actions){
            diaryManagementService.recordAction(action);
        }
    }
}
