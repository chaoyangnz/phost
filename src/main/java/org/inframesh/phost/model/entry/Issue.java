package org.inframesh.phost.model.entry;

import org.inframesh.phost.model.AbstractDTO;
import org.inframesh.phost.util.annotation.Column;
import org.inframesh.phost.util.annotation.JdbcType;
import org.inframesh.phost.util.annotation.Table;

/**
 * @author ${user}
 *
 * ${tags}
 */

/**
 * Table Name: ISSUE
 *
 * Generated by ormapper plug-in
 */

@Table(name="ISSUE")
public class Issue extends AbstractDTO
{
    private static final Long serialVersionUID = -6460591374460294934L;
	@Column(pk=true, name="ISSUE_ID", type=JdbcType.BIGINT, nullable=false)
    private Long issueId;


    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public Long getIssueId() {
        return this.issueId;
    }

}
