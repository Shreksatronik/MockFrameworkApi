 class MockArgs {

        private EnumArgs type;
        private Object val;

        MockArgs(EnumArgs type, Object val) {
            this.type = type;
            this.val = val;
        }

        EnumArgs getType() {
            return type;
        }

        Object getVal() {
            return val;
        }
    }

