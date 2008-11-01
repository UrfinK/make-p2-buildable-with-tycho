/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.equinox.p2.tests.ui.dialogs;

import org.eclipse.equinox.internal.p2.ui.model.AvailableUpdateElement;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfileRegistry;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.ui.ProvUI;
import org.eclipse.equinox.internal.provisional.p2.ui.dialogs.UpdateWizard;
import org.eclipse.equinox.internal.provisional.p2.ui.policy.Policy;
import org.eclipse.equinox.p2.tests.ui.AbstractProvisioningUITest;
import org.eclipse.jface.wizard.WizardDialog;

/**
 * Tests for the install wizard
 */
public class UpdateWizardTest extends AbstractProvisioningUITest {

	/**
	 * Tests the wizard
	 */
	public void testWizard() {
		// this test is fairly useless until we seed it with real data and some mock profile, but
		// at least we open the wizard up.
		UpdateWizard wizard = new UpdateWizard(Policy.getDefault(), IProfileRegistry.SELF, new IInstallableUnit[0], new AvailableUpdateElement[0], new Object[0], null);
		WizardDialog dialog = new WizardDialog(ProvUI.getDefaultParentShell(), wizard);
		dialog.setBlockOnOpen(false);
		dialog.open();

		try {
			assertFalse(wizard.canFinish());

		} finally {
			dialog.getShell().close();
		}
	}
}