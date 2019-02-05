/*******************************************************************************
 * Copyright (c) 2019 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.intellij.openshift.ui.component;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.jboss.tools.intellij.openshift.utils.odo.ComponentType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CreateComponentDialog extends DialogWrapper {
  private JPanel contentPane;
  private JComboBox sourceTypeComboBox;
  private JTextField sourceTextField;
  private JComboBox componentTypeComboBox;
  private JComboBox componentVersionComboBox;
  private JTextField nameTextField;
  private JButton browseButton;

  public CreateComponentDialog(Component parent) {
    super(null, false, IdeModalityType.IDE);
    $$$setupUI$$$();
    init();
    setTitle("Create component");
    browseButton.addActionListener(new ActionListener() {
                                     @Override
                                     public void actionPerformed(ActionEvent e) {
                                       ProjectSelectionDialog dialog = new ProjectSelectionDialog(CreateComponentDialog.this.getWindow());
                                       dialog.show();
                                       if (dialog.isOK()) {
                                         sourceTextField.setText(dialog.getSelectedProject().getBasePath());
                                       }
                                     }
                                   }
    );

  }


  public static void main(String[] args) {
    CreateComponentDialog dialog = new CreateComponentDialog(null);
    dialog.show();
    System.exit(0);
  }

  public void setComponentTypes(ComponentType[] componentTypes) {
    componentTypeComboBox.setRenderer(new DefaultListCellRenderer() {
      @Override
      public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        value = ((ComponentType) value).getName();
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
      }
    });
    componentTypeComboBox.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          componentVersionComboBox.setModel(new DefaultComboBoxModel(((ComponentType) e.getItem()).getVersions().split(",")));
        }
      }
    });
    componentTypeComboBox.setModel(new DefaultComboBoxModel(componentTypes));
    componentTypeComboBox.setSelectedIndex(-1);
    componentTypeComboBox.setSelectedIndex(0);
  }

  @NotNull
  @Override
  protected List<ValidationInfo> doValidateAll() {
    List<ValidationInfo> validations = new ArrayList<>();
    if (nameTextField.getText().length() == 0) {
      validations.add(new ValidationInfo("Name must be provided", nameTextField));
    }
    if (sourceTextField.getText().length() == 0) {
      validations.add(new ValidationInfo("Source must be provided", sourceTextField));
    }
    return validations;
  }

  public String getName() {
    return nameTextField.getText();
  }

  public String getSource() {
    return sourceTextField.getText();
  }

  public int getSourceType() {
    return sourceTypeComboBox.getSelectedIndex();
  }

  public String getComponentType() {
    return ((ComponentType) componentTypeComboBox.getSelectedItem()).getName();
  }

  public String getComponentVersion() {
    return (String) componentVersionComboBox.getSelectedItem();
  }

  private void createUIComponents() {
    // TODO: place custom component creation code here
  }

  @Nullable
  @Override
  protected JComponent createCenterPanel() {
    return contentPane;
  }

  /**
   * Method generated by IntelliJ IDEA GUI Designer
   * >>> IMPORTANT!! <<<
   * DO NOT edit this method OR call it in your code!
   *
   * @noinspection ALL
   */
  private void $$$setupUI$$$() {
    contentPane = new JPanel();
    contentPane.setLayout(new GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), -1, -1));
    final JPanel panel1 = new JPanel();
    panel1.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
    contentPane.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    final JLabel label1 = new JLabel();
    label1.setText("Source type");
    panel1.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JLabel label2 = new JLabel();
    label2.setText("Source");
    panel1.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JLabel label3 = new JLabel();
    label3.setText("Component type");
    panel1.add(label3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JLabel label4 = new JLabel();
    label4.setText("Component version");
    panel1.add(label4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JLabel label5 = new JLabel();
    label5.setText("Name");
    panel1.add(label5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    sourceTypeComboBox = new JComboBox();
    final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
    defaultComboBoxModel1.addElement("Local");
    defaultComboBoxModel1.addElement("Git");
    sourceTypeComboBox.setModel(defaultComboBoxModel1);
    panel1.add(sourceTypeComboBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    sourceTextField = new JTextField();
    panel1.add(sourceTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    nameTextField = new JTextField();
    panel1.add(nameTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    componentTypeComboBox = new JComboBox();
    panel1.add(componentTypeComboBox, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    browseButton = new JButton();
    browseButton.setText("Browse");
    panel1.add(browseButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    componentVersionComboBox = new JComboBox();
    panel1.add(componentVersionComboBox, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
  }

  /**
   * @noinspection ALL
   */
  public JComponent $$$getRootComponent$$$() {
    return contentPane;
  }
}
