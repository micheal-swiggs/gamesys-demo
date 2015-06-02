package gamesys

import org.springframework.security.core.context.SecurityContextHolder

class GamesysTagLib {
    static defaultEncodeAs = [taglib:'none']
    static returnObjectForTags = ['secRoleCheck', 'secVisibleEmployeeAttrs']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def secHasRole = { attrs, body ->
        def auth = SecurityContextHolder.getContext().getAuthentication()
        def authorities = auth.getAuthorities().collect{ it.getAuthority() }
        if (authorities.contains(attrs['role'])) {
            out << body()
        }
    }

    def secRoleCheck = { attrs, body ->
        def auth = SecurityContextHolder.getContext().getAuthentication()
        def authorities = auth.getAuthorities().collect{ it.getAuthority() }
        return authorities.contains(attrs['role'])
    }

    def secVisibleEmployeeAttrs = { attrs, body ->
        def allAttrs = [
            ['Full Name', 'fullName'],
            ['Start Date', 'startDate'],
            ['Holiday Allowance', 'holidayAllowance'],
            ['Date Created', 'dateCreated' ],
            ['Start Date', 'lastUpdated' ]
        ]

        def auth = SecurityContextHolder.getContext().getAuthentication()
        def authorities = auth.getAuthorities().collect{ it.getAuthority() }
        if(authorities.contains('ROLE_HR')) return allAttrs
        if(authorities.contains('ROLE_STANDARD')) return allAttrs[0..1]
        return []
    }
}
