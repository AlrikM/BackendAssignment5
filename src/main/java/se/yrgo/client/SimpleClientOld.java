package se.yrgo.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.domain.Action;
import se.yrgo.domain.Call;
import se.yrgo.services.calls.CallHandlingService;
import se.yrgo.services.customers.CustomerManagementService;
import se.yrgo.services.customers.CustomerNotFoundException;
import se.yrgo.services.diary.DiaryManagementService;

import java.util.*;

public class SimpleClientOld {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                 new ClassPathXmlApplicationContext("application.xml");
        CustomerManagementService service =
                 context.getBean("customerManagementMockImpl", CustomerManagementService.class);
        CallHandlingService callHandlingService =
                context.getBean("callHandlingServiceImpl", CallHandlingService.class);
        DiaryManagementService diaryManagementService =
                context.getBean("diaryManagementServiceMockImpl", DiaryManagementService.class);
        System.out.println(service.getAllCustomers());
        Call newCall = new Call("Larry Wall called from Acme Corp");
        Action action1 = new Action("Call back Larry to ask how things are going", new GregorianCalendar(2016, 0, 0), "rac");
        Action action2 = new Action("Check our sales dept to make sure Larry is being tracked", new GregorianCalendar(2016, 0, 0), "rac");

        List<Action> actions = new ArrayList<Action>();
        actions.add(action1);
        actions.add(action2);

        try{
            callHandlingService.recordCall("OB74", newCall, actions);
        }catch (CustomerNotFoundException e){
            System.out.println("That customer doesn't exist");
        }

        System.out.println("Here are the outstanding actions:");
        Collection<Action> incompleteActions = diaryManagementService.getAllIncompleteActions("rac");
        for (Action next: incompleteActions){
            System.out.println(next);
        }

        context.close();
    }
}