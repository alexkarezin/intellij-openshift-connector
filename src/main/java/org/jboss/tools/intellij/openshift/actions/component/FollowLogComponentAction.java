/*******************************************************************************
 * Copyright (c) 2019 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.intellij.openshift.actions.component;

import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jboss.tools.intellij.openshift.tree.application.ComponentNode;
import org.jboss.tools.intellij.openshift.utils.odo.Odo;

public class FollowLogComponentAction extends ShowLogComponentAction {

  @Override
  protected String getTelemetryActionName() { return "follow component log"; }

  @Override
  public void actionPerformed(AnActionEvent anActionEvent, Object selected, Odo odo) {
    doLog((ComponentNode) selected, odo, true);
  }
}
