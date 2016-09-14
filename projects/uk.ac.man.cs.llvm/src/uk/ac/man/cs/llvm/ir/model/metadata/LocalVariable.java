/*
 * Copyright (c) 2016, Oracle and/or its affiliates.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided
 * with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package uk.ac.man.cs.llvm.ir.model.metadata;

import uk.ac.man.cs.llvm.ir.model.MetadataBlock;
import uk.ac.man.cs.llvm.ir.model.MetadataBlock.MetadataReference;

public class LocalVariable implements MetadataNode {

    protected MetadataReference context = MetadataBlock.voidRef;
    protected MetadataReference name = MetadataBlock.voidRef;
    protected MetadataReference file = MetadataBlock.voidRef;
    protected long line;
    protected long arg;
    protected MetadataReference type = MetadataBlock.voidRef;
    protected long flags;

    public LocalVariable() {
    }

    public MetadataReference getContext() {
        return context;
    }

    public void setContext(MetadataReference context) {
        this.context = context;
    }

    public MetadataReference getName() {
        return name;
    }

    public void setName(MetadataReference name) {
        this.name = name;
    }

    public MetadataReference getFile() {
        return file;
    }

    public void setFile(MetadataReference file) {
        this.file = file;
    }

    public long getLine() {
        return line;
    }

    public void setLine(long line) {
        this.line = line;
    }

    public long getArg() {
        return arg;
    }

    public void setArg(long arg) {
        this.arg = arg;
    }

    public MetadataReference getType() {
        return type;
    }

    public void setType(MetadataReference type) {
        this.type = type;
    }

    public long getFlags() {
        return flags;
    }

    public void setFlags(long flags) {
        this.flags = flags;
    }

    @Override
    public String toString() {
        return "LocalVariable [context=" + context + ", name=" + name + ", file=" + file + ", line=" + line + ", arg=" + arg + ", type=" + type + ", flags=" + flags + "]";
    }
}
