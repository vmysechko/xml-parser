<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:gunsspace="http://aaa.com/purchase"
    exclude-result-prefixes="xs"
    version="2.0">
    <xsl:template match="/">
    <html>
        <body>
        <h2>Guns table</h2>
            <title>GUNS</title>
            <table border="1">
                <th>
                    <td>ID</td>
                    <td>Model</td>
                    <td>Handy</td>
                    <td>Origin</td>
                    <td>Distance</td>
                    <td>Charger</td>
                    <td>Optics</td>
                    <td>Material</td>
                </th>
                <xsl:for-each select="guns/gun">
                    <tr>
                        <td><xsl:value-of select="@id"/></td>
                        <td><xsl:value-of select="model"/></td>
                        <td><xsl:value-of select="handy" /></td>
                        <td><xsl:value-of select="origin"/></td>
                        <td><xsl:value-of select="parameters/gunsspace:distance"/></td>
                        <td><xsl:value-of select="parameters/gunsspace:charger"/></td>
                        <td><xsl:value-of select="parameters/gunsspace:optics"/></td>
                        <td><xsl:value-of select="material"/></td>
                    </tr>
                </xsl:for-each>
            </table>
        </body>
    </html>
    </xsl:template>
</xsl:stylesheet>