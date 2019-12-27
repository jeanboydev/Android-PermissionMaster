package com.jeanboy.component.permission.core;

import com.jeanboy.component.permission.constants.Type;
import com.jeanboy.component.permission.core.module.DefaultModule;
import com.jeanboy.component.permission.core.module.OverlaysModule;

/**
 * @author caojianbo
 * @since 2019/12/27 14:22
 */
public class RangerFactory {

    public static Ranger build(int type) {
        switch (type) {
            case Type.OVERLAYS:
                return new OverlaysModule();
            default:
                return new DefaultModule();
        }
    }
}
