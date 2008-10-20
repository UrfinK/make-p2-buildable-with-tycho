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
package org.eclipse.equinox.p2.tests.metadata;

import org.eclipse.equinox.internal.provisional.p2.metadata.*;
import org.eclipse.equinox.p2.tests.AbstractProvisioningTest;
import org.eclipse.osgi.service.resolver.VersionRange;
import org.osgi.framework.Version;

/**
 * Black box tests for API of {@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit}.
 */
public class InstallableUnitTest extends AbstractProvisioningTest {
	/**
	 * Tests for {@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#satisfies(org.eclipse.equinox.internal.provisional.p2.metadata.RequiredCapability)}.
	 */
	public void testSatisfies() {
		ProvidedCapability[] provides = new ProvidedCapability[] {MetadataFactory.createProvidedCapability("testNamespace", "name", new Version(1, 0, 0))};
		IInstallableUnit iu = createIU("iu", provides);

		RequiredCapability wrongNamespace = MetadataFactory.createRequiredCapability("wrongNamespace", "name", VersionRange.emptyRange, null, false, false);
		RequiredCapability wrongName = MetadataFactory.createRequiredCapability("testNamespace", "wrongName", VersionRange.emptyRange, null, false, false);
		RequiredCapability lowerVersionRange = MetadataFactory.createRequiredCapability("testNamespace", "name", new VersionRange("[0.1,1.0)"), null, false, false);
		RequiredCapability higherVersionRange = MetadataFactory.createRequiredCapability("testNamespace", "name", new VersionRange("(1.0,99.99]"), null, false, false);
		RequiredCapability match = MetadataFactory.createRequiredCapability("testNamespace", "name", new VersionRange("[1.0,2.0)"), null, false, false);

		assertFalse("1.0", iu.satisfies(wrongNamespace));
		assertFalse("1.1", iu.satisfies(wrongName));
		assertFalse("1.2", iu.satisfies(lowerVersionRange));
		assertFalse("1.3", iu.satisfies(higherVersionRange));
		assertTrue("1.4", iu.satisfies(match));
	}
}