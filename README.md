package org.nnnn.ddd.service;

import java.util.List;
import org.nnnn.ddd.model.User;
public interface ADSearchService {
    List<User> findAllUsers();

    List<User> findAllUsersWithSealedAccess();

    List<User> findMembersOfGroup(String... groupDn);

    public User findUser(final String username);
}


=============

package org.nnnn.ddd.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.Attributes;

import org.nnnn.ddd.AppConstants;
import org.nnnn.ddd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Service;
import static org.springframework.ldap.query.LdapQueryBuilder.query;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Profile({ "test", "dev", "local", "test-users" })
public class ADSearchServiceDev extends ADSearchServiceProd {
    @Autowired
    private LdapTemplate ldapTemplate;

    public User findUser(final String username) {
        User user = super.findUser(username);
        if (user != null) {
                return user;
        }
         List<User> userList = testUsers();
         for (User testUser: userList) {
                if (testUser.getUsername().equalsIgnoreCase(username)) {
                        return testUser;
                }
         }
         return null;

    }

    @Cacheable("userList")
    public List<User> findAllUsers() {
        List<User> allUsers = super.findAllUsers();
        allUsers.addAll(testUsers());
        return allUsers;
    }


    @Cacheable("sealedUserList")
    public List<User> findAllUsersWithSealedAccess() {
        List<User> allUsers = super.findAllUsersWithSealedAccess();
        List<User> userList = testUsers();
        for (User testUser : userList) {
            if (testUser.getUsername().equalsIgnoreCase("T-SG-ddd-ANALYST-BRONX")) {
                allUsers.add(testUser);
                break;
            }
        }
        return allUsers;
    }


    final String jsonUsers = "[\n" +
            "  {\n" +
            "    \"username\": \"T-SG-ddd-SUPERVISOR\",\n" +
            "    \"firstName\": \"John\",\n" +
            "    \"lastName\": \"ddd-SUPERVISOR\",\n" +
            "    \"email\": \"SG-ddd-SUPERVISOR@nnnn.org\",\n" +
            "    \"rank\": \"Sgt\",\n" +
            "    \"title\": \"\",\n" +
            "    \"tax\": \"\",\n" +
            "    \"cmdCode\": \"\",\n" +
            "    \"mobile\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"username\": \"T-SG-ddd-ANALYST-BROOKLYN\",\n" +
            "    \"firstName\": \"Mike\",\n" +
            "    \"lastName\": \"SG-ddd-ANALYST-BROOKLYN\",\n" +
            "    \"email\": \"SG-ddd-ANALYST-BROOKLYN@nnnn.org\",\n" +
            "    \"rank\": \"Sgt\",\n" +
            "    \"title\": \"\",\n" +
            "    \"tax\": \"\",\n" +
            "    \"cmdCode\": \"\",\n" +
            "    \"mobile\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"username\": \"T-SG-ddd-ANALYST-BRONX\",\n" +
            "    \"firstName\": \"Chris\",\n" +
            "    \"lastName\": \"SG-ddd-ANALYST-BRONX\",\n" +
            "    \"email\": \"SG-ddd-ANALYST-BRONX@nnnn.org\",\n" +
            "    \"rank\": \"Sgt\",\n" +
            "    \"title\": \"\",\n" +
            "    \"tax\": \"\",\n" +
            "    \"cmdCode\": \"\",\n" +
            "    \"mobile\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"username\": \"T-SG-ddd-ANALYST-QUEENS\",\n" +
            "    \"firstName\": \"Elizabeth\",\n" +
            "    \"lastName\": \"SG-ddd-ANALYST-QUEENS\",\n" +
            "    \"email\": \"SG-ddd-ANALYST-QUEENS@nnnn.org\",\n" +
            "    \"rank\": \"Sgt\",\n" +
            "    \"title\": \"\",\n" +
            "    \"tax\": \"\",\n" +
            "    \"cmdCode\": \"\",\n" +
            "    \"mobile\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"username\": \"T-SG-ddd-ANALYST-SI\",\n" +
            "    \"firstName\": \"Cindy\",\n" +
            "    \"lastName\": \"SG-ddd-ANALYST-SI\",\n" +
            "    \"email\": \"SG-ddd-ANALYST-SI@nnnn.org\",\n" +
            "    \"rank\": \"Sgt\",\n" +
            "    \"title\": \"\",\n" +
            "    \"tax\": \"\",\n" +
            "    \"cmdCode\": \"\",\n" +
            "    \"mobile\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"username\": \"T-SG-ddd-ANALYST-MANHATTAN\",\n" +
            "    \"firstName\": \"Jose\",\n" +
            "    \"lastName\": \"SG-ddd-ANALYST-MANHATTAN\",\n" +
            "    \"email\": \"SG-ddd-ANALYST-MANHATTAN@nnnn.org\",\n" +
            "    \"rank\": \"Sgt\",\n" +
            "    \"title\": \"\",\n" +
            "    \"tax\": \"\",\n" +
            "    \"cmdCode\": \"\",\n" +
            "    \"mobile\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"username\": \"T-SG-ddd-ANALYST-SNP\",\n" +
            "    \"firstName\": \"Jennifer\",\n" +
            "    \"lastName\": \"SG-ddd-ANALYST-SNP\",\n" +
            "    \"email\": \"SG-ddd-ANALYST-SNP@nnnn.org\",\n" +
            "    \"rank\": \"Sgt\",\n" +
            "    \"title\": \"\",\n" +
            "    \"tax\": \"\",\n" +
            "    \"cmdCode\": \"\",\n" +
            "    \"mobile\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"username\": \"T-SG-ddd-ANALYST-REMANDED\",\n" +
            "    \"firstName\": \"Brian\",\n" +
            "    \"lastName\": \"SG-ddd-ANALYST-REMANDED\",\n" +
            "    \"email\": \"SG-ddd-ANALYST-REMANDED@nnnn.org\",\n" +
            "    \"rank\": \"Sgt\",\n" +
            "    \"title\": \"\",\n" +
            "    \"tax\": \"\",\n" +
            "    \"cmdCode\": \"\",\n" +
            "    \"mobile\": \"\"\n" +
            "  },\n" +
            " {\n" +
            "    \"username\": \"T-SG-ddd-DUAL\",\n" +
            "    \"firstName\": \"AnthonyR\",\n" +
            "    \"lastName\": \"T-SG-ddd-DUAL\",\n" +
            "    \"email\": \"T-SG-ddd-DUAL@nnnn.org\",\n" +
            "    \"rank\": \"Sgt\",\n" +
            "    \"title\": \"\",\n" +
            "    \"tax\": \"\",\n" +
            "    \"cmdCode\": \"\",\n" +
            "    \"mobile\": \"\"\n" +
            "  }\n" +
            "]";

    public List<User> testUsers() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            // Convert JSON array into List<User>
            return mapper.readValue(jsonUsers, new TypeReference<List<User>>() {
            });
        } catch (Exception e) {
            // Log error if needed
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

 
}


==============

package org.nnnn.ddd.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.Attributes;

import org.nnnn.ddd.AppConstants;
import org.nnnn.ddd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.ContainerCriteria;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Service;
import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
@Profile({"prod", "uat"})
public class ADSearchServiceProd implements ADSearchService {
    @Autowired
    private LdapTemplate ldapTemplate;

    public User findUser(final String username) {
        LdapQuery query = query()
                .where("objectClass").is("user")
                .and("sAMAccountName").is(username);

        List<User> userList = ldapTemplate.search(query, (Attributes attrs) -> {
            User user = new User();
            user.setUsername(attrs.get("sAMAccountName").get().toString());
            user.setFirstName(attrs.get("givenName").get().toString());
            user.setLastName(attrs.get("sn").get().toString());
            user.setMobile(attrs.get("telephoneNumber") != null ? attrs.get("telephoneNumber").get().toString() : "");
            user.setEmail(attrs.get("mail") != null ? attrs.get("mail").get().toString() : "");
            user.setRank(attrs.get("nnnnrank") != null ? attrs.get("nnnnrank").get().toString() : "");
            user.setTitle(attrs.get("title") != null ? attrs.get("title").get().toString() : "");
            user.setTax(attrs.get("nnnntaxid") != null ? attrs.get("nnnntaxid").get().toString() : "");
            user.setCmdCode(attrs.get("nnnncmdcode") != null ? attrs.get("nnnncmdcode").get().toString() : "");
            return user;
        });
        if (userList.size() < 1)
            return null;
        return userList.get(0);
    }

    @Cacheable("userList")
    public List<User> findAllUsers(){
        List<User> allUsers = new ArrayList<User>();
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_SUPERVISOR+",OU=ddd,OU=Application Control,OU=nnnn Groups,DC=nnnn,DC=finest"));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_BK+",OU=ddd,OU=Application Control,OU=nnnn Groups,DC=nnnn,DC=finest"));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_BX+",OU=ddd,OU=Application Control,OU=nnnn Groups,DC=nnnn,DC=finest"));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_MN+",OU=ddd,OU=Application Control,OU=nnnn Groups,DC=nnnn,DC=finest"));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_QN+",OU=ddd,OU=Application Control,OU=nnnn Groups,DC=nnnn,DC=finest"));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_SI+",OU=ddd,OU=Application Control,OU=nnnn Groups,DC=nnnn,DC=finest"));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_SNP+",OU=ddd,OU=Application Control,OU=nnnn Groups,DC=nnnn,DC=finest"));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_RMD+",OU=ddd,OU=Application Control,OU=nnnn Groups,DC=nnnn,DC=finest"));
        return allUsers;
    }

    @Cacheable("sealedUserList")
    public List<User> findAllUsersWithSealedAccess(){
        List<User> allUsers = new ArrayList<User>();
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_SUPERVISOR+",OU=ddd,OU=Application Control,OU=nnnn Groups,DC=nnnn,DC=finest", AppConstants.SG_SEALED_EVENT_DN));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_BK+",OU=ddd,OU=Application Control,OU=nnnn Groups,DC=nnnn,DC=finest", AppConstants.SG_SEALED_EVENT_DN));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_BX+",OU=ddd,OU=Application Control,OU=nnnn Groups,DC=nnnn,DC=finest", AppConstants.SG_SEALED_EVENT_DN));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_MN+",OU=ddd,OU=Application Control,OU=nnnn Groups,DC=nnnn,DC=finest", AppConstants.SG_SEALED_EVENT_DN));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_QN+",OU=ddd,OU=Application Control,OU=nnnn Groups,DC=nnnn,DC=finest", AppConstants.SG_SEALED_EVENT_DN));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_SI+",OU=ddd,OU=Application Control,OU=nnnn Groups,DC=nnnn,DC=finest", AppConstants.SG_SEALED_EVENT_DN));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_SNP+",OU=ddd,OU=Application Control,OU=nnnn Groups,DC=nnnn,DC=finest", AppConstants.SG_SEALED_EVENT_DN));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_RMD+",OU=ddd,OU=Application Control,OU=nnnn Groups,DC=nnnn,DC=finest", AppConstants.SG_SEALED_EVENT_DN));
        return allUsers;
    }

    public List<User> findMembersOfGroup(String... groupDns) {
        if (groupDns.length == 0) {
            return new ArrayList<User>();
        }
        ContainerCriteria query = query()
                .where("objectClass").is("user");
        for (int i = 0; i < groupDns.length; i++) {
            query.and("memberOf").is(groupDns[i]);
        }

        return ldapTemplate.search(query, (Attributes attrs) -> {
            User user = new User();
            user.setUsername(attrs.get("sAMAccountName").get().toString());
            user.setFirstName(attrs.get("givenName")!=null?attrs.get("givenName").get().toString():"");
            user.setLastName(attrs.get("sn")!=null?attrs.get("sn").get().toString():"");
            user.setMobile(attrs.get("telephoneNumber")!=null?attrs.get("telephoneNumber").get().toString():"");
            user.setEmail(attrs.get("mail")!=null?attrs.get("mail").get().toString():"");
            user.setRank(attrs.get("nnnnrank")!=null?attrs.get("nnnnrank").get().toString():"");
            user.setTitle(attrs.get("title")!=null?attrs.get("title").get().toString():"");
            user.setTax(attrs.get("nnnntaxid")!=null?attrs.get("nnnntaxid").get().toString():"");
            user.setCmdCode(attrs.get("nnnncmdcode")!=null?attrs.get("nnnncmdcode").get().toString():"");
            return user;
        });
    }

}


=====
