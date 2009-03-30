/*******************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.equinox.internal.p2.ui.admin;

import org.eclipse.equinox.internal.provisional.p2.repository.IRepository;

import java.net.URI;
import org.eclipse.core.runtime.*;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.equinox.internal.provisional.p2.ui.operations.AddRepositoryOperation;
import org.eclipse.equinox.internal.provisional.p2.ui.operations.ProvisioningUtil;

/**
 * Operation that adds a metadata repository given its URL.
 * 
 * @since 3.4
 */
public class AddMetadataRepositoryOperation extends AddRepositoryOperation {

	public AddMetadataRepositoryOperation(String label, URI location) {
		super(label, new URI[] {location});
	}

	protected IStatus doBatchedExecute(IProgressMonitor monitor) throws ProvisionException {
		SubMonitor mon = SubMonitor.convert(monitor, locations.length);
		for (int i = 0; i < locations.length; i++) {
			ProvisioningUtil.addMetadataRepository(locations[i], notify);
			mon.worked(1);
		}
		return okStatus();
	}

	protected void setNickname(URI location, String nickname) throws ProvisionException {
		for (int i = 0; i < locations.length; i++) {
			ProvisioningUtil.setMetadataRepositoryProperty(location, IRepository.PROP_NICKNAME, nickname);
		}
	}
}
