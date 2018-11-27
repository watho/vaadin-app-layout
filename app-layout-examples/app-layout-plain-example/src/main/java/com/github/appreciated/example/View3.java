package com.github.appreciated.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "view3", layout = MainAppLayout.class)
public class View3 extends VerticalLayout {

  public View3() {
    Button button = new Button("Change menu entry");
    button.addClickListener(e -> clicked());

    add(button);
  }

  private void clicked() {
    Notification.show(MainAppLayout.homeElement.getCaption());
    MainAppLayout.homeElement.setText("test");
    Notification.show(MainAppLayout.homeElement.getCaption());
  }
}