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
 * Table Name: PROJECT_META
 *
 * Generated by ormapper plug-in
 */

@Table(name="PROJECT_META")
public class ProjectMeta extends AbstractDTO
{
    private static final Long serialVersionUID = 3806763234652659985L;

	@Column(pk=true, name="PROJECT_ID", type=JdbcType.BIGINT, nullable=false)
    private Long projectId;

    @Column(name="NAME", type=JdbcType.VARCHAR)
    private String name;

    @Column(name="SUMMARY", type=JdbcType.VARCHAR)
    private String summary;

    @Column(name="DESCRIPTION", type=JdbcType.VARCHAR)
    private String description;

    @Column(name="CODE_LICENSE", type=JdbcType.VARCHAR)
    private String codeLicense;

    @Column(name="CONTENT_LICENSE", type=JdbcType.VARCHAR)
    private String contentLicense;
    
    @Column(name="VERSION_CONTROL", type=JdbcType.VARCHAR)
    private String versionControl;

    @Column(name="LOGO", type=JdbcType.VARCHAR)
    private String logo;


    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setCodeLicense(String codeLicense) {
        this.codeLicense = codeLicense;
    }

    public String getCodeLicense() {
        return this.codeLicense;
    }

    public void setContentLicense(String contentLicense) {
        this.contentLicense = contentLicense;
    }

    public String getContentLicense() {
        return this.contentLicense;
    }
    
    public void setVersionControl(String versionControl) {
        this.versionControl = versionControl;
    }

    public String getVersionControl() {
        return this.versionControl;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogo() {
        return this.logo;
    }

}