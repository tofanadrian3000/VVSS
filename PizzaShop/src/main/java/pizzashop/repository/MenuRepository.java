package pizzashop.repository;

import pizzashop.model.MenuData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MenuRepository {
    private final String menuFileName = "data/menu.txt";
    private List<MenuData> listMenu;

    public MenuRepository(){
    }

    private void readMenu(){
        ClassLoader classLoader = MenuRepository.class.getClassLoader();
        BufferedReader menuBufferReader = null;
        this.listMenu= new ArrayList<>();
        File menuFile = new File(classLoader.getResource(menuFileName).getFile());

        try {
            menuBufferReader = new BufferedReader(new FileReader(menuFile));
            String currentLine;

            while ((currentLine = menuBufferReader.readLine()) != null) {
                MenuData menuItem = this.getMenuItem(currentLine);
                if (null != menuItem) {
                    listMenu.add(menuItem);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != menuBufferReader) {
                try {
                    menuBufferReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private MenuData getMenuItem(String line){
        if (line==null|| line.equals("")) {
            return null;
        }
        MenuData newMenu;
        StringTokenizer lineTokenizer=new StringTokenizer(line, ",");
        String name= lineTokenizer.nextToken();
        double price = Double.parseDouble(lineTokenizer.nextToken());
        newMenu = new MenuData(name, 0, price);
        return newMenu;
    }

    public List<MenuData> getMenu(){
        readMenu();//create a new menu for each table, on request
        return listMenu;
    }

}
