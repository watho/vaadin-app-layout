package com.github.appreciated.app.layout.test.lefthybridsmall.view;

import com.github.appreciated.app.layout.test.lefthybridsmall.LeftHybridSmallBehaviourView;
import com.github.appreciated.app.layout.test.view.ExampleView;
import com.vaadin.flow.router.Route;

@Route(value = "view6", layout = LeftHybridSmallBehaviourView.class) // an empty view name will also be the default view
public class View6 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
