public class Args {
        private ArgumentMatchers type;
        private Object val;

        Args(ArgumentMatchers type, Object val) {
            this.type = type;
            this.val = val;
        }

        ArgumentMatchers getType() {
            return type;
        }

        Object getVal() {
            return val;
        }
    }

