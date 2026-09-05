public class M2CrossPackageInheritanceReach {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

        if (fieldModifier.equals("public"))
            return "ALLOWED";

        if (fieldModifier.equals("private"))
            return accessorContext.equals("SAME_CLASS")
                    ? "ALLOWED" : "DENIED";

        if (fieldModifier.equals("default"))
            return (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE"))
                    ? "ALLOWED" : "DENIED";

        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";

            if (accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                return "ALLOWED";

            return "DENIED";
        }

        return "DENIED";
    }

    static String describeContext(String context) {

        String[] words = context.toLowerCase().split("_");
        String result = "";

        for (String word : words) {
            result += Character.toUpperCase(word.charAt(0))
                    + word.substring(1) + " ";
        }

        return result.trim();
    }

    public static void main(String[] args) {

        System.out.println(
            classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
            )
        );

        System.out.println(
            classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
            )
        );

        System.out.println(
            describeContext(
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
            )
        );
    }
}