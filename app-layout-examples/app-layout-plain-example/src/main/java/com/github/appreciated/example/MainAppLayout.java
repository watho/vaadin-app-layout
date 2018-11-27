package com.github.appreciated.example;

import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.entity.Section.HEADER;

import java.util.function.Consumer;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.appmenu.MenuHeaderComponent;
import com.github.appreciated.app.layout.component.appmenu.left.LeftClickableComponent;
import com.github.appreciated.app.layout.component.appmenu.left.LeftNavigationComponent;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.appmenu.top.TopClickableComponent;
import com.github.appreciated.app.layout.component.appmenu.top.TopNavigationComponent;
import com.github.appreciated.app.layout.component.appmenu.top.builder.TopAppMenuBuilder;
import com.github.appreciated.app.layout.design.AppLayoutDesign;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;

/**
 * The main view contains a button and a template element.
 */

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainAppLayout extends AppLayoutRouterLayout {
  private Behaviour variant;
  private DefaultNotificationHolder notifications;
  private DefaultBadgeHolder        badge;
//  private Thread currentThread;
  public static LeftNavigationComponent homeElement;

  @Override public com.github.appreciated.app.layout.behaviour.AppLayout getAppLayout() {
    if (variant == null) {
      variant = Behaviour.LEFT_HYBRID;
      notifications = new DefaultNotificationHolder(newStatus -> {
      });
      badge = new DefaultBadgeHolder();
    }

    if (!variant.isTop()) {
      homeElement = new LeftNavigationComponent("Home", VaadinIcon.HOME.create(), View1.class);
      return AppLayoutBuilder
          .get(variant)
          .withTitle("App Layout")
          .withAppBar(AppBarBuilder
                          .get()
                          .add(new AppBarNotificationButton(VaadinIcon.BELL, notifications))
                          .build())
          .withDesign(AppLayoutDesign.MATERIAL)
          .withAppMenu(LeftAppMenuBuilder
                           .get()
                           .addToSection(new MenuHeaderComponent("Menu-Header",
                                                                 "Version 2.0.1",
                                                                 null
                           ), HEADER)
                           .addToSection(new LeftClickableComponent("Set Behaviour HEADER",
                                                                    VaadinIcon.COG.create(),
                                                                    clickEvent -> openModeSelector(variant)
                           ), HEADER)
              .add(homeElement)
                           .add(new LeftNavigationComponent("Grid", VaadinIcon.TABLE.create(), GridTest.class))
                           .add(LeftSubMenuBuilder
                                    .get("My Submenu", VaadinIcon.PLUS.create())
                                    .add(LeftSubMenuBuilder
                                             .get("My Submenu", VaadinIcon.PLUS.create())
                                             .add(new LeftNavigationComponent("Charts",
                                                                              VaadinIcon.SPLINE_CHART.create(),
                                                                              View2.class
                                             ))
                                             .add(new LeftNavigationComponent("Contact",
                                                                              VaadinIcon.CONNECT.create(),
                                                                              View3.class
                                             ))
                                             .add(new LeftNavigationComponent("More",
                                                                              VaadinIcon.COG.create(),
                                                                              View4.class
                                             ))
                                             .build())
                                    .add(new LeftNavigationComponent("Contact1",
                                                                     VaadinIcon.CONNECT.create(),
                                                                     View3.class
                                    ))
                                    .add(new LeftNavigationComponent("More1", VaadinIcon.COG.create(), View4.class))
                                    .build())
                           .add(new LeftNavigationComponent("Menu", VaadinIcon.MENU.create(), View5.class))
                           .addToSection(new LeftClickableComponent("Set Behaviour FOOTER",
                                                                    VaadinIcon.COG.create(),
                                                                    clickEvent -> openModeSelector(variant)
                           ), FOOTER)
                           .build())
          .build();
    } else {
      return AppLayoutBuilder
          .get(variant)
          .withTitle("App Layout")
          .withAppBar(AppBarBuilder
                          .get()
                          .add(new AppBarNotificationButton(VaadinIcon.BELL, notifications))
                          .build())
          .withDesign(AppLayoutDesign.MATERIAL)
          .withAppMenu(TopAppMenuBuilder
                           .get()
                           .addToSection(new TopClickableComponent("Set Behaviour 1",
                                                                   VaadinIcon.COG.create(),
                                                                   clickEvent -> openModeSelector(variant)
                           ), HEADER)
                           .add(new TopNavigationComponent("Home", VaadinIcon.HOME.create(), View1.class))
                           .add(new TopNavigationComponent("Contact", VaadinIcon.SPLINE_CHART.create(), View2.class))
                           .addToSection(new TopClickableComponent("Set Behaviour 2",
                                                                   VaadinIcon.COG.create(),
                                                                   clickEvent -> openModeSelector(variant)
                           ), FOOTER)
                           .addToSection(
                               new TopNavigationComponent("More", VaadinIcon.CONNECT.create(), View3.class),
                               FOOTER
                                        )
                           .build())
          .build();
    }
  }

  public LeftNavigationComponent getHomeElement() {
    return homeElement;
  }

  private void setDrawerVariant(Behaviour variant) {
    this.variant = variant;
    reloadConfiguration();
  }

  private void openModeSelector(Behaviour variant) {
    new BehaviourSelector(variant, this::setDrawerVariant).open();
  }

  class BehaviourSelector extends Dialog {
    BehaviourSelector(Behaviour current, Consumer<Behaviour> consumer) {
      VerticalLayout layout = new VerticalLayout();
      add(layout);
      RadioButtonGroup<Behaviour> group = new RadioButtonGroup<>();
      group
          .getElement()
          .getStyle()
          .set("display", "flex");
      group
          .getElement()
          .getStyle()
          .set("flexDirection", "column");
      group.setItems(Behaviour.LEFT,
                     Behaviour.LEFT_OVERLAY,
                     Behaviour.LEFT_RESPONSIVE,
                     Behaviour.LEFT_HYBRID,
                     Behaviour.LEFT_HYBRID_SMALL,
                     Behaviour.LEFT_RESPONSIVE_HYBRID,
                     Behaviour.LEFT_RESPONSIVE_HYBRID_NO_APP_BAR,
                     Behaviour.LEFT_RESPONSIVE_HYBRID_OVERLAY_NO_APP_BAR,
                     Behaviour.LEFT_RESPONSIVE_OVERLAY,
                     Behaviour.LEFT_RESPONSIVE_OVERLAY_NO_APP_BAR,
                     Behaviour.LEFT_RESPONSIVE_SMALL,
                     Behaviour.LEFT_RESPONSIVE_SMALL_NO_APP_BAR,
                     Behaviour.TOP,
                     Behaviour.TOP_LARGE
                    );
      group.setValue(current);
      layout.add(group);
      group.addValueChangeListener(singleSelectionEvent -> {
        consumer.accept(singleSelectionEvent.getValue());
        close();
      });
    }
  }
}
