<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	
	<xsl:output encoding="UTF-8"/>
	<xsl:param name="deploy"/>
	
	<xsl:template match="/configuration">
		<xml-data>
			<xsl:apply-templates select="@*|node()"/>
		</xml-data>
	</xsl:template>
	
	<xsl:template match="@*|node()">
		<xsl:if test="@deploy=$deploy or not(@deploy)">
			<xsl:copy>
				<xsl:apply-templates select="@*|node()"/>
			</xsl:copy>
		</xsl:if>
	</xsl:template>
	
</xsl:stylesheet>