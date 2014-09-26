
package test.annotation;

import java.lang.reflect.Field;

public class DemoActivity {
    private static final int R_id_xxxView = 10000;

    @InjectView(value = R_id_xxxView, tag = "view_tag")
    public View mView;

    @InjectView(value = R_id_xxxView + 1, tag = "view_tag2")
    public View mView2;

    public DemoActivity() {
        InjectView(this);
    }

    private void InjectView(DemoActivity demoActivity) {
        /**
         * in Roboguice, try to find
         */
        Field[] fields = demoActivity.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectView injectView = field.getAnnotation(InjectView.class);
            if (injectView != null) {
                int viewId = injectView.value();
                String viewTag = injectView.tag();
                // 模拟 view = findViewById(viewId)
                View view = new View(viewId, viewTag);

                // 通过反射设置回去
                try {
                    field.setAccessible(true);
                    field.set(demoActivity, view);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class View {
        private int mId = -1;
        private String mTag = "";

        public View(int mId, String mTag) {
            this.mId = mId;
            this.mTag = mTag;
        }

        @Override
        public String toString() {
            return super.toString() + ", mId=" + mId + ", mTag=" + mTag;
        }
    }
}
