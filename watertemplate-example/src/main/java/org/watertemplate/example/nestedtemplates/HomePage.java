package org.watertemplate.example.nestedtemplates;

import org.watertemplate.Template;
import org.watertemplate.TemplateMap;
import org.watertemplate.TemplateObject;

public class HomePage extends Template {

    @Override
    protected Template getMasterTemplate() {
        return new MasterTemplate("Home");
    }

    @Override
    protected String getFilePath() {
        return "nestedtemplates/homepage.html";
    }
}
