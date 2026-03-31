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
            user.setRank(attrs.get("NNNNrank") != null ? attrs.get("NNNNrank").get().toString() : "");
            user.setTitle(attrs.get("title") != null ? attrs.get("title").get().toString() : "");
            user.setTax(attrs.get("NNNNtaxid") != null ? attrs.get("NNNNtaxid").get().toString() : "");
            user.setCmdCode(attrs.get("NNNNcmdcode") != null ? attrs.get("NNNNcmdcode").get().toString() : "");
            return user;
        });
        if (userList.size() < 1)
            return null;
        return userList.get(0);
    }

    @Cacheable("userList")
    public List<User> findAllUsers(){
        List<User> allUsers = new ArrayList<User>();
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_SUPERVISOR+",OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest"));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_BK+",OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest"));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_BX+",OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest"));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_MN+",OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest"));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_QN+",OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest"));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_SI+",OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest"));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_SNP+",OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest"));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_RMD+",OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest"));
        return allUsers;
    }

    @Cacheable("sealedUserList")
    public List<User> findAllUsersWithSealedAccess(){
        List<User> allUsers = new ArrayList<User>();
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_SUPERVISOR+",OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest", AppConstants.SG_SEALED_EVENT_DN));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_BK+",OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest", AppConstants.SG_SEALED_EVENT_DN));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_BX+",OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest", AppConstants.SG_SEALED_EVENT_DN));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_MN+",OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest", AppConstants.SG_SEALED_EVENT_DN));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_QN+",OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest", AppConstants.SG_SEALED_EVENT_DN));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_SI+",OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest", AppConstants.SG_SEALED_EVENT_DN));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_SNP+",OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest", AppConstants.SG_SEALED_EVENT_DN));
        allUsers.addAll(findMembersOfGroup("CN="+AppConstants.SG_ANALYST_RMD+",OU=DDD,OU=Application Control,OU=NNNN Groups,DC=nnnn,DC=finest", AppConstants.SG_SEALED_EVENT_DN));
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
            user.setRank(attrs.get("NNNNrank")!=null?attrs.get("NNNNrank").get().toString():"");
            user.setTitle(attrs.get("title")!=null?attrs.get("title").get().toString():"");
            user.setTax(attrs.get("NNNNtaxid")!=null?attrs.get("NNNNtaxid").get().toString():"");
            user.setCmdCode(attrs.get("NNNNcmdcode")!=null?attrs.get("NNNNcmdcode").get().toString():"");
            return user;
        });
    }

}
