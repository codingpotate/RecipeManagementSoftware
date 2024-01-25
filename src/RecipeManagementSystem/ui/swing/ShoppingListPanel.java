package RecipeManagementSystem.ui.swing;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShoppingListPanel extends JPanel {

    private JTextArea shoppingListTextArea;

    public ShoppingListPanel() {
        initializeUI();
        createComponents();
        setUpLayout();
    }

    private void initializeUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEtchedBorder());
    }

    private void createComponents() {
        shoppingListTextArea = new JTextArea();
        shoppingListTextArea.setEditable(false);
    }

    private void setUpLayout() {
        add(new JLabel("Shopping List:"));
        add(new JScrollPane(shoppingListTextArea));
    }

    public void displayShoppingList(List<String> shoppingList) {
        StringBuilder shoppingListText = new StringBuilder();
        for (String item : shoppingList) {
            shoppingListText.append(item).append("\n");
        }
        shoppingListTextArea.setText(shoppingListText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 300);

                ShoppingListPanel shoppingListPanel = new ShoppingListPanel();
                List<String> sampleShoppingList = List.of(
                        "Item 1: 1 unit",
                        "Item 2: 2 units",
                        "Item 3: 3 units"
                );
                shoppingListPanel.displayShoppingList(sampleShoppingList);

                frame.add(shoppingListPanel);
                frame.setVisible(true);
            }
        });
    }
}
