package dong.factory.mode.factory;

import dong.factory.mode.cloth.Cloth;

/**
 * Created by Dong on 2017/10/30 0030.
 */

public abstract class AbstractClothFactory {

    public abstract <T extends Cloth> T makeCloth(Class<T> clazz);
}
