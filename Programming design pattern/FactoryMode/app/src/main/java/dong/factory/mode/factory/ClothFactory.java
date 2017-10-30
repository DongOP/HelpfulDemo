package dong.factory.mode.factory;

import dong.factory.mode.cloth.Cloth;

/**
 * Created by Dong on 2017/10/30 0030.
 */

public class ClothFactory extends AbstractClothFactory {

    public ClothFactory() {
    }

    private static final ClothFactory mClothFactory = new ClothFactory();

    public static ClothFactory getInstance() {
        return mClothFactory;
    }

    @Override
    public <T extends Cloth> T makeCloth(Class<T> clazz) {
        Cloth cloth = null;
        try {
            cloth = (Cloth)Class.forName(clazz.getName()).newInstance();
        } catch (Exception e) {
            System.out.println("需要填入实现了cloth类的类名");
        }
        return (T)cloth;
    }
}
