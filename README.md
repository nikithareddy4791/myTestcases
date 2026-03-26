List<String> groups = jwt.getClaimAsStringList("nnnn Groups");
            if (groups != null) {
                for (String g : groups) {
