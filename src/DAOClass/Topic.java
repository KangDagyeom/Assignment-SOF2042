/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClass;

/**
 *
 * @author Hyun
 */
public class Topic {

    private String title;
    private String description;
    private String iconPath;

    public Topic(String title, String description, String iconPath) {
        this.title = title;
        this.description = description;
        this.iconPath = iconPath;
    }
    
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getIconPath() {
        return iconPath;
    }
}
