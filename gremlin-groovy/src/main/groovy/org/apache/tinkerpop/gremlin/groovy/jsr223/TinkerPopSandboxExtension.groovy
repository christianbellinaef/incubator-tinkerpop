/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.tinkerpop.gremlin.groovy.jsr223

import org.codehaus.groovy.ast.MethodNode

import java.util.function.BiPredicate

/**
 * @author Stephen Mallette (http://stephen.genoprime.com)
 */
class TinkerPopSandboxExtension extends SandboxExtension {

    TinkerPopSandboxExtension() {
        gIsAlwaysGraphTraversalSource = false
        graphIsAlwaysGraphInstance = false

        def gremlinRoot = "org\\.apache\\.tinkerpop\\.gremlin"
        def gremlinRootStructure = "$gremlinRoot\\.structure"
        def gremlinRootProcess = "$gremlinRoot\\.process"
        def methodWhiteList = ["java\\.util\\..*",
                               "org\\.codehaus\\.groovy\\.runtime\\.DefaultGroovyMethods",
                               "$gremlinRootStructure\\..*",
                               "$gremlinRootProcess\\..*",
                               "$gremlinRootProcess\\.traversal\\.dsl\\.graph\\..*"]

        methodFilter = (BiPredicate<String, MethodNode>) { descriptor, method ->
            methodWhiteList.any { descriptor =~ it }
        }
    }
}
