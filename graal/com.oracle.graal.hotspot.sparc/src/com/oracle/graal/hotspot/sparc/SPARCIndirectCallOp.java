/*
 * Copyright (c) 2012, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.oracle.graal.hotspot.sparc;

import static com.oracle.graal.lir.LIRInstruction.OperandFlag.REG;
import static jdk.vm.ci.code.ValueUtil.asRegister;
import static jdk.vm.ci.sparc.SPARC.g5;

import com.oracle.graal.asm.sparc.SPARCMacroAssembler;
import com.oracle.graal.hotspot.HotSpotVMConfig;
import com.oracle.graal.lir.LIRFrameState;
import com.oracle.graal.lir.LIRInstructionClass;
import com.oracle.graal.lir.Opcode;
import com.oracle.graal.lir.asm.CompilationResultBuilder;
import com.oracle.graal.lir.sparc.SPARCCall;
import com.oracle.graal.lir.sparc.SPARCCall.IndirectCallOp;

import jdk.vm.ci.code.Register;
import jdk.vm.ci.meta.ResolvedJavaMethod;
import jdk.vm.ci.meta.Value;

/**
 * A register indirect call that complies with the extra conventions for such calls in HotSpot. In
 * particular, the metaspace Method of the callee must be in g5 for the case where a vtable entry's
 * _from_compiled_entry is the address of an C2I adapter. Such adapters expect the target method to
 * be in g5.
 */
@Opcode("CALL_INDIRECT")
final class SPARCIndirectCallOp extends IndirectCallOp {
    public static final LIRInstructionClass<SPARCIndirectCallOp> TYPE = LIRInstructionClass.create(SPARCIndirectCallOp.class);
    public static final SizeEstimate SIZE = SizeEstimate.create(2);

    /**
     * Vtable stubs expect the metaspace Method in g5.
     */
    public static final Register METHOD = g5;

    @Use({REG}) protected Value metaspaceMethod;

    private final HotSpotVMConfig config;

    SPARCIndirectCallOp(ResolvedJavaMethod targetMethod, Value result, Value[] parameters, Value[] temps, Value metaspaceMethod, Value targetAddress, LIRFrameState state, HotSpotVMConfig config) {
        super(TYPE, SIZE, targetMethod, result, parameters, temps, targetAddress, state);
        this.metaspaceMethod = metaspaceMethod;
        this.config = config;
    }

    @Override
    public void emitCode(CompilationResultBuilder crb, SPARCMacroAssembler masm) {
        crb.recordMark(config.MARKID_INLINE_INVOKE);
        Register callReg = asRegister(targetAddress);
        assert !callReg.equals(METHOD);
        SPARCCall.indirectCall(crb, masm, callReg, callTarget, state);
    }

    @Override
    public void verify() {
        super.verify();
        assert asRegister(metaspaceMethod).equals(METHOD);
    }
}
