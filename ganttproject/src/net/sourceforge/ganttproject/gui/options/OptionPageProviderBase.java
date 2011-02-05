package net.sourceforge.ganttproject.gui.options;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import net.sourceforge.ganttproject.IGanttProject;
import net.sourceforge.ganttproject.gui.UIFacade;
import net.sourceforge.ganttproject.gui.options.model.GPOptionGroup;
import net.sourceforge.ganttproject.gui.options.model.OptionPageProvider;
import net.sourceforge.ganttproject.language.GanttLanguage;

public abstract class OptionPageProviderBase implements OptionPageProvider {
    private String myPageID;
    private IGanttProject myProject;
    private UIFacade myUiFacade;

    protected OptionPageProviderBase(String pageID) {
        myPageID = pageID;
    }

    public String getPageID() {
        return myPageID;
    }
    
    public boolean hasCustomComponent() {
        return false;
    }
    
    public Component buildPageComponent() {
        return null;
    }

    public void init(IGanttProject project, UIFacade uiFacade) {
        myProject = project;
        myUiFacade = uiFacade;
    }
    
    public void commit() {
        for (GPOptionGroup optionGroup : getOptionGroups()) {
            optionGroup.commit();
        }
    }

    public abstract GPOptionGroup[] getOptionGroups();
    
    protected IGanttProject getProject() {
        return myProject;
    }
    
    protected UIFacade getUiFacade() {
        return myUiFacade;
    }

    @Override
    public String toString() {
        return GanttLanguage.getInstance().getText(new OptionsPageBuilder.I18N().getCanonicalOptionPageTitleKey(getPageID()));
    }

    protected static JPanel wrapContentComponent(Component contentComponent, String title, String description) {
        JPanel result = new JPanel(new BorderLayout());
        result.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        result.add(new TopPanel(title, description), BorderLayout.NORTH);
        result.add(contentComponent, BorderLayout.CENTER);
        return result;

    }
}