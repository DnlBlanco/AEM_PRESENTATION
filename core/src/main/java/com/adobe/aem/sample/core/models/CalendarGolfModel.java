package com.adobe.aem.sample.core.models;

import javax.inject.Inject;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CalendarGolfModel{

     @Inject
     private List<Event> event;

     public List<Event> getEvent(){
         return event;
     }

}
