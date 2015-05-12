/*
 * Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

import java.lang.module.Layer;
import java.security.Provider;
import java.util.ServiceLoader;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

/*
 * @test
 * @run testng BasicLayerTest
 */

public class BasicLayerTest {

    @Test
    public void testEmptyLayer() {
        ServiceLoader<Provider> sl
            = ServiceLoader.load(Layer.emptyLayer(), Provider.class);
        assertFalse(sl.iterator().hasNext());
    }

    @Test
    public void testBootLayer() {
        ServiceLoader<Provider> sl
            = ServiceLoader.load(Layer.bootLayer(), Provider.class);
        boolean found = false;
        for (Provider provider : sl) {
            if (provider.getName().equals("SunJCE"))
                found = true;
        }
        assertTrue(found);
    }

    @Test(expectedExceptions = { NullPointerException.class })
    public void testNullLayer() {
        ServiceLoader.load(null, Provider.class);
    }

    @Test(expectedExceptions = { NullPointerException.class })
    public void testNullService() {
        ServiceLoader.load(Layer.emptyLayer(), null);
    }

}
